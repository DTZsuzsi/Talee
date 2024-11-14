import { useEffect, useState } from "react";
import HomeCard from "../molecules/HomeCard.jsx";
import StateChangeButton from "../molecules/StateChangeButton.jsx";
import TagOptions from "../../../tag/components/TagOptions.jsx";
import TagCard from "../../../tag/components/TagCard.jsx";

const Home = () => {
    const [mode, setMode] = useState('locations');

    const [events, setEvents] = useState();
    const [locations, setLocations] = useState();
    const [tags, setTags]=useState(null);
    const [addNewTag, setAddNewTag]=useState(false);

    useEffect(() => {
        async function fetchEvents() {
            const response = await fetch('/api/events');
            const data = await response.json();
            setEvents(data);
        }

        async function fetchLocations() {
            const response = await fetch('/api/locations');
            const data = await response.json();
            setLocations(data);
        }

        async function fetchTags(){
            const response= await fetch("/api/tags");
            const data= await response.json();
            setTags(data);
        }

        fetchEvents();
        fetchLocations();
        fetchTags();
    }, [addNewTag]);

    
    

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


async function handleNewTag(id, e) {
    setAddNewTag(false);
  
    const selectedTagName = e.target.value;
    const tagToSend = findTag(selectedTagName);
  
    const response = await fetch(`/api/events/${id}`, {
      method: "POST",
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(tagToSend),
    });
  
    const data = await response.json();
    console.log(data);
    setAddNewTag(true);
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
  
  // Render event cards
  let eventCards = [];
  if (events) {
    console.log(events);
    eventCards = events?.map((event) => (
      <div key={event.id}>
        <HomeCard
          key={event.id}
          title={event.name}
          href={`/events/${event.id}`}
          description={event.description}
          date={event.date}
        />
        <TagOptions onChange={(e) => handleNewTag(event.id, e)} />
       <ul className="flex flex-wrap justify-around">
        {event.tags?.map((tag) => (
          <li key={tag.id} className="mx-auto">
            <TagCard tag={tag} />
          </li>
        ))}
        </ul>
      </div>
    ));
  }
  

    let locationCards = [];
    if (locations) locationCards = locations.map((location) => (<HomeCard key={location.id} title={location.name} href={`/locations/${location.id}`} description={location.description} date={location.date}></HomeCard>));

    return (
        <div className="w-full mx-auto p-4">
            <div className="flex flex-col">
                <div className="w-full max-w-sm">
                    <div className="h-20 flex justify-between items-center">
                        <StateChangeButton onClick={setEventState} active={mode == 'events'}>Events</StateChangeButton>
                        <StateChangeButton onClick={setLocationState}  active={mode == 'locations'}>Locations</StateChangeButton>
                    </div>
                </div>
                <div className="grow">
                    <h1 className="text-3xl font-bold my-5 text-center">{mode == "events" ? "Events" : "Locations"}</h1>
                    <div className="flex w-full flex-col">
                        {mode == "events" ? eventCards : locationCards}
                    </div>
                </div>
            </div>
        </div>
    )
};

export default Home;