/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import TaleeButton from "../main/components/atoms/TaleeButton.jsx";
import EventForm from "./components/EventForm.jsx";
import TagListModify from "../main/components/molecules/TagListModify.jsx";
import useFetchTags from "../main/components/hooks/useFetchTags.jsx"
import { useFetchEventData } from "./hooks/useFetchEventData.jsx";
import axiosInstance from "../../axiosInstance.jsx";

function ModifyEventForm() {
  const { eventId } = useParams();
  const navigate = useNavigate();
const {tags}=useFetchTags();
const { event, error, loading, user, owner, setEvent }= useFetchEventData(eventId);

  const token = localStorage.getItem("jwtToken");

  async function handleModifyingEvent(e) {
    e.preventDefault();
    console.log("Submitting event:", event);

    try {
      const response = await axiosInstance.patch(`/events/${eventId}/modify`, event);

      if (!response.ok)
        throw new Error(`Failed to modify event: ${response.statusText}`);

      const data = await response.json();
      console.log("Modified event data:", data);

      navigate(`/events/${eventId}`);
    } catch (error) {
      console.error("Error modifying event:", error);
    }
  }

  return (
    event && (
      <div className="flex justify-center py-10">
        <form
          onSubmit={handleModifyingEvent}
          className="bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl"
        >
          <h1 className="font-bold text-3xl text-center mb-8">Modify Event</h1>

         <EventForm event={event} setEvent={setEvent}/>

         <TagListModify partName={event} setter={setEvent} tags={tags}/>

          <div className="w-full flex justify-center">
            <TaleeButton type="submit" className="mt-5">
              Modify Event
            </TaleeButton>
          </div>
        </form>
      </div>
    )
  );
}

export default ModifyEventForm;
