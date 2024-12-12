import {useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import TaleeButton from "../main/components/atoms/TaleeButton.jsx";
import ServerError from "../main/components/atoms/ServerError";
import Loading from "../main/components/atoms/Loading";
import useFetchTags from "../main/components/hooks/useFetchTags.jsx";
import {useFetchLocationData} from "../locations/hooks/useFetchLocationData.jsx";
import axiosInstance from "../../axiosInstance.jsx";
import TagListModify from "../main/components/molecules/TagListModify.jsx";
import EventFormNew from "./components/EventFormNew.jsx";

function NewEventForm() {
  const { locationId } = useParams();
  const {location}=useFetchLocationData(locationId);
  const [newEvent, setNewEvent] = useState({
    date: "",
    name: "",
    description: "",
    locationInEventDTO: { locationId: locationId, name: location?.name },
    owner: "",
    size: "SMALL",
    status: "COMING",
    tags: [],
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

 const {tags}=useFetchTags();




  async function handleNewEvent(e) {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await axiosInstance.post("/events",newEvent);

      if (!response.ok) throw new Error(`Failed to create event`);

      const createdEventId = await response.json();
      navigate(`/events/${createdEventId}`);
    } catch (error) {
      console.error("Error creating event:", error);
      setError("Failed to create event");
    } finally {
      setLoading(false);
    }
  }

  if (error) {
    return <ServerError error={error} />;
  }

  return (
    <div className="flex justify-center py-10">
      <form
        onSubmit={handleNewEvent}
        className="bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl"
      >
        <h1 className="font-bold text-3xl text-center mb-8">
          Create New Event
        </h1>
        <EventFormNew newEvent={newEvent} setNewEvent={setNewEvent} location={location} />

        <TagListModify tags={tags} partName={"event"} setter={setNewEvent} />
        <div className="w-full flex justify-center">
          {loading ? (
            <Loading />
          ) : (
            <TaleeButton type="submit" className="mt-5">
              Create Event
            </TaleeButton>
          )}
        </div>
      </form>
    </div>
  );
}

export default NewEventForm;
