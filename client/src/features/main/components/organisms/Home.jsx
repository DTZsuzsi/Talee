import { useEffect, useState } from "react";
import HomeCard from "../molecules/HomeCard.jsx";
import StateChangeButton from "../molecules/StateChangeButton.jsx";

const Home = () => {
    const [mode, setMode] = useState('locations');

    const [events, setEvents] = useState();
    const [locations, setLocations] = useState();

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

        fetchEvents();
        fetchLocations();
    }, []);

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

    let eventCards = []; 
    if (events) eventCards = events.map((event) => (<HomeCard key={event.id} title={event.name} href={`/events/${event.id}`} description={event.description} date={event.date}></HomeCard>));

    let locationCards = [];
    if (locations) locationCards = locations.map((location) => (<HomeCard key={location.id} title={location.name} href={`/locations/${location.id}`} description={location.description} date={location.date}></HomeCard>));

    return (
        <div className="w-full mx-auto p-4">
            <div className="flex">
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