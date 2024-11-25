/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import HomeCard from "../molecules/HomeCard.jsx";
import StateChangeButton from "../molecules/StateChangeButton.jsx";
import TagCard from "../../../tag/components/TagCard.jsx";
/** @format */

import Loading from '../atoms/Loading.jsx';

const Home = () => {
   

 
    const [locations, setLocations] = useState();
    const [tagChange, setTagChange]=useState(false);
	const [mode, setMode] = useState('locations');
	const [loading, setLoading] = useState(false);
	const [events, setEvents] = useState();


	useEffect(() => {
		async function fetchEvents() {
			const response = await fetch('/api/events');
			const data = await response.json();
			if (!response.ok) {
				return;
			}
			setEvents(data);
			setLoading(false);
		}

        

      
        async function fetchLocations() {
			const response = await fetch('/api/locations');
			const data = await response.json();
			if (!response.ok) {
				return;
			}
			setLocations(data);
			setLoading(false);
		}

        fetchEvents();
        fetchLocations();
    }, [tagChange]);

    	

	const [darkMode, setDarkMode] = useState(false);

	// Effect to apply the dark mode class to the body
	useEffect(() => {
		if (darkMode) {
			document.documentElement.classList.add('dark');
		} else {
			document.documentElement.classList.remove('dark');
		}
	}, [darkMode]);

	function setEventState() {
		setMode('events');
		setDarkMode(true);
	}

	function setLocationState() {
		setMode('locations');
		setDarkMode(false);
	}



 
  
async  function handleDeleteTag(event, tag){
    setTagChange(false);
const response= await fetch(`/api/events/tag/${event.id}?tagId=${tag.id}`, {method: "DELETE"});
const data= await response.json();
setTagChange(true);
  }

  async function handleDeleteTagFromLocation(location, tag){
	setTagChange(false);
	const response= await fetch(`/api/locations/tag/${location.id}?tagId=${tag.id}`, {method: "DELETE"});
	const data= await response.json();
	setTagChange(true);
  }


  // Render event cards
  let eventCards = [];
  if (events) {
   
    eventCards = events?.map((event) => (
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
    ));
  }
  
	

	let locationCards = [];
	if (locations)
		locationCards = locations.map(location => (
	<div key={location.id}> 
			<HomeCard
				
				title={location.name}
				href={`/locations/${location.id}`}
				description={location.description}
				date={location.date}
			></HomeCard>
			<ul className="flex flex-wrap justify-around">
			{location.locationTags?.map((tag) => (
			  <li key={tag.id} className="mx-auto">
				<TagCard tag={tag} onClick={()=>handleDeleteTagFromLocation(location, tag)} color={tag.color}/>
			  </li>
			))}
			</ul>
			</div>
		));

	return (
		<div className='w-full mx-auto p-4'>
			<div className='flex flex-col'>
				<div className='w-full max-w-sm'>
					<div className='h-20 flex justify-between items-center'>
						<StateChangeButton
							onClick={setEventState}
							active={mode == 'events'}
						>
							Events
						</StateChangeButton>
						<StateChangeButton
							onClick={setLocationState}
							active={mode == 'locations'}
						>
							Locations
						</StateChangeButton>
					</div>
				</div>
				<div className='grow'>
					<h1 className='text-3xl font-bold my-5 text-center'>
						{mode == 'events' ? 'Events' : 'Locations'}
					</h1>
					{loading ? (
						<Loading />
					) : (
						<div className='flex w-full flex-col'>
							{mode == 'events' ? eventCards : locationCards}
						</div>
					)}
				</div>
			</div>
		</div>
	);
};

export default Home;
