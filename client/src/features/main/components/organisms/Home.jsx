/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import HomeCard from "../molecules/HomeCard.jsx";
import StateChangeButton from "../molecules/StateChangeButton.jsx";
import TagCard from "../../../tag/components/TagCard.jsx";
/** @format */

import Loading from "../atoms/Loading.jsx";
import GoogleMapComponent from "../../../maps/GoogleMapComponent.jsx";
const Home = () => {
  const [locations, setLocations] = useState();
  const [mode, setMode] = useState("locations");
  const [loading, setLoading] = useState(false);
  const [events, setEvents] = useState();
  const position = { lat: 53.54992, lng: 10.00678 };
  const googleAPIKEY = "AIzaSyCpdQIVDmlFx3hXi3tz6DN59hXWMJEqLOU";

  useEffect(() => {
    async function fetchEvents() {
      const response = await fetch("/api/events");
      const data = await response.json();
      if (!response.ok) {
        return;
      }
      setEvents(data);
      setLoading(false);
    }

    async function fetchLocations() {
      const response = await fetch("/api/locations/all");
      const data = await response.json();
      if (!response.ok) {
        return;
      }
      setLocations(data);
      setLoading(false);
    }

    fetchEvents();
    fetchLocations();
  }, []);

  const [darkMode, setDarkMode] = useState(false);

  // Effect to apply the dark mode class to the body
  useEffect(() => {
    if (darkMode) {
      document.documentElement.classList.add("dark");
    } else {
      document.documentElement.classList.remove("dark");
    }
  }, [darkMode]);

  function setEventState() {
    setMode("events");
    setDarkMode(true);
  }

  function setLocationState() {
    setMode("locations");
    setDarkMode(false);
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
      </div>
    ));
  }

  let locationCards = [];
  if (locations)
    locationCards = locations.map((location) => (
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
              <TagCard
                tag={tag}
                onClick={() => {
                  console.log("hi");
                }}
                color={tag.color}
              />
            </li>
          ))}
        </ul>
      </div>
    ));

  return (
    <div className="w-full mx-auto p-4">
      <div className="flex flex-col items-center">
        <div className="w-full max-w-sm">
          <div className="h-20 flex items-center">
            <StateChangeButton
              onClick={setEventState}
              active={mode === "events"}
            >
              Events
            </StateChangeButton>
            <StateChangeButton
              onClick={setLocationState}
              active={mode === "locations"}
            >
              Locations
            </StateChangeButton>
          </div>
        </div>
        <div className="grow">
          <h1 className="text-3xl font-bold my-5 text-center">
            {mode === "events" ? "Events" : "Locations"}
          </h1>
          {loading ? (
            <Loading />
          ) : (
            <div className="flex w-full flex-col">
              {mode === "events" ? eventCards : locationCards}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Home;
