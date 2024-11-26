/* eslint-disable no-unused-vars */
import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { HiMiniPencilSquare } from 'react-icons/hi2';
import { MdDeleteForever } from 'react-icons/md';
import ServerError from '../main/components/atoms/ServerError';
import Loading from '../main/components/atoms/Loading';
import TagCard from '../tag/components/TagCard';
import TagOptions from '../tag/components/TagOptions';
import HomeCard from '../main/components/molecules/HomeCard';

function LocationDetailPage() {
  const [error, setError] = useState(null);
  const [location, setLocation] = useState(null);
  const { locationId } = useParams();
  const [tags, setTags] = useState(null);
  const [tagChange, setTagChange] = useState(false);
const [events, setEvents]=useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    async function fetchLocation() {
      if (!locationId) {
        console.error('Location ID is undefined');
        return;
      }

      const response = await fetch(`/api/locations/${locationId}`);

      if (response.ok) {
        const data = await response.json();

        setLocation(data);
      } else {
        setError(`Failed to fetch location with id: ${locationId}, ${response.statusText}`);
      }
    }

    async function fetchEvents() {
        if (!locationId) {
          console.error('Location ID is undefined');
          return;
        }
  
        const response = await fetch(`/api/events/locations/${locationId}`);
  
        if (response.ok) {
          const data = await response.json();
  
          setEvents(data);
        } else {
          setError(`Failed to fetch location with id: ${locationId}, ${response.statusText}`);
        }
      }

    async function fetchTags() {
      const response = await fetch('/api/tags');
      const data = await response.json();
      setTags(data);
    }

    fetchLocation();
    fetchTags();
    fetchEvents();
  }, [locationId, tagChange]);

  async function handleNewTag(id, e) {
    setTagChange(false);

    const selectedTagName = e.target.value;
    const tagToSend = findTag(selectedTagName);

    const response = await fetch(`/api/locations/${id}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(tagToSend),
    });

    const data = await response.json();

    setTagChange(true);
  }

  function findTag(tagName) {
    let tagFound = {};
    for (const tag of tags) {
      if (tag.name === tagName) {
        tagFound = tag;
      }
    }
    return tagFound;
  }

  async function handleDeleteTag(location, tag) {
    setTagChange(false);
    const response = await fetch(`/api/locations/tag/${location.id}?tagId=${tag.id}`, { method: 'DELETE' });
    const data = await response.json();
    setTagChange(true);
  }

  async function deleteLocation() {
    const response = await fetch(`/api/locations/${locationId}`, {
      method: 'DELETE',
    });

    const data = await response.json();
    navigate('/');
  }
  if (error) {
    return <ServerError error={error} />;
  }

  return location ? (
    <div className='flex flex-col items-center justify-center h-screen'>
      <div className='p-1 border-slate-300 shadow-md shadow-slate-800 border-2 rounded-md m-2 w-[50%] min-w-[540px] bg-slate-300 '>
        <img
          src='https://images.unsplash.com/photo-1513151233558-d860c5398176?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
          className='self-center m-auto rounded-t-md'
        ></img>
        <p className='text-xl font-semibold px-2'>Name: </p>
        <p className='text-xl  px-2 mb-2'> {location.name}</p>
        <p className='text-xl font-semibold px-2'> Address: </p>
        <p className='text-xl  px-2 mb-2'> {location.address}</p>
        <p className='text-xl font-semibold px-2'> Opening Hours: </p>
        {location.openingHours &&
          location.openingHours.map((openingHour) => (
            <div key={openingHour.day} className='mt-1'>
              <p className='text-sm px-2 mb-2'>
                {' '}
                {openingHour.day}: {openingHour.openingTime} - {openingHour.closingTime}
              </p>
            </div>
          ))}
        <p className='text-l font-semibold px-2 mb-2'> {location.description}</p>
        <p className='text-l font-semibold px-2 mb-2'> Phone: {location.phone}</p>
        <ul className='flex flex-wrap justify-around'>
          {location?.locationTags?.map((tag) => (
            <li key={tag?.id} className='mx-auto'>
              <TagCard tag={tag} onClick={() => handleDeleteTag(location, tag)} color={tag?.color} />
            </li>
          ))}
        </ul>
        <TagOptions onChange={(e) => handleNewTag(location.id, e)} />
      </div>
      <div>
        <Link to={`/locations/${locationId}/update`}>
          <HiMiniPencilSquare className='h-10 w-10 text-blue-600 mr-2' />
        </Link>
        <MdDeleteForever className='h-10 w-10 text-blue-600 mr-2' onClick={deleteLocation} />
      </div>
{events?.map((event)=> <div key={event.id}>     
<div key={event.id}>
        <HomeCard
          key={event.id}
          title={event.name}
          href={`/events/${event.id}`}
          description={event.description}
          date={event.date}
        />
       <ul className="flex flex-wrap justify-around">
        {event.tags?.map((tag) => (
          <li key={tag.id} className="mx-auto">
            <TagCard tag={tag} onClick={()=>handleDeleteTag(event, tag)} color={tag.color}/>
          </li>
        ))}
        </ul>
      </div>



</div>)}

    </div>
  ) : (
    <Loading />
  );
}

export default LocationDetailPage;
