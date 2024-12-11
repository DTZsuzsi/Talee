/* eslint-disable no-unused-vars */
import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import TaleeButton from '../main/components/atoms/TaleeButton.jsx';
import Loading from '../main/components/atoms/Loading';
import ServerError from '../main/components/atoms/ServerError';

import LocationForm from './LocationForm.jsx';
import { useFetchTags } from '../main/components/hooks/useFetchTags.jsx';
import { useTagHandlers } from '../main/tagHandling/useTagHandlers.jsx'
import useOpeningHours from './hooks/useOpeningHours.jsx';
import axios from 'axios';
import TagListModify from '../main/components/molecules/TagListModify.jsx';
function UpdateLocationForm() {
  const { locationId } = useParams();
  const [location, setLocation] = useState(null);
  const [tagChange, setTagChange] = useState(false);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();
  const { tags } = useFetchTags();

  const { handleNewTag, handleDeleteTag } = useTagHandlers(location, setLocation, tags);
  const { handleOpeningHoursChange } = useOpeningHours(setLocation);

  const token = localStorage.getItem('jwtToken');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axios.get(`/api/locations/${locationId}`);
        console.log(data);
        setLocation(data);
      } catch (err) {
        console.error('Error fetching locations:', err);
      }
    };

    fetchData();
  }, [locationId]);

  async function handleLocationUpdate(e) {
    e.preventDefault();
    setLoading(true);
    try {
      await axios.patch(`/api/locations`, location, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      navigate(`/locations/${locationId}`);
    } catch (error) {
      console.error('Error updating location:', error);
      setError('Failed to update location');
    } finally {
      setLoading(false);
    }
  }

  if (error) {
    return <ServerError error={error} />;
  }

  return (
    location && (
      <div className='flex justify-center py-10'>
        <form
          onSubmit={handleLocationUpdate}
          className='bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl'
        >
          <h1 className='font-bold text-3xl text-center mb-8'>Update Location</h1>

          <LocationForm location={location} setLocation={setLocation} onHoursChange={handleOpeningHoursChange} />

          {/* <div className='w-full my-5'>
            <TagOptions onChange={handleNewTag} />
            <ul className='grid grid-cols-2 md:grid-cols-4 gap-4'>
              {location.tags?.map((tag) => (
                <li key={tag.id}>
                  <TagCard tag={tag} onClick={() => handleDeleteTag(tag)} color={tag.color} className='w-full' />
                </li>
              ))}
            </ul>
          </div> */}

          <TagListModify location={location} setLocation={setLocation} tags={tags} />

          <div className='w-full flex justify-center mt-6'>
            {loading ? (
              <Loading />
            ) : (
              <TaleeButton type='submit' className='mt-5'>
                Update Location
              </TaleeButton>
            )}
          </div>
        </form>
      </div>
    )
  );
}

export default UpdateLocationForm;
