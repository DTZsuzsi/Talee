/* eslint-disable no-unused-vars */
import {  useState } from "react";
import { Link, useParams } from "react-router-dom";

import TagCard from "../tag/components/TagCard";
import BiggerOnHover from "../main/components/atoms/BiggerOnHover.jsx";
import UserCard from "../users/components/UserCard.jsx";
import { useFetchEventData } from "./hooks/useFetchEventData.jsx";
import ActionButtons from "../main/components/molecules/ActionButtons.jsx";
import EventInfo from "./EventInfo.jsx";

function EventDetailPage() {
  const { eventId } = useParams();
  const [userChange, setUserChange] = useState(false);

 const { event, error, loading, user, owner }=useFetchEventData(eventId);

 
  async function handleDeleteUser(event, user) {
    setUserChange(false);
    const response = await fetch(
      `/api/events/user/${event.id}?userId=${user.id}`,
      { method: "DELETE" },
    );
    const data = await response.json();
    console.log(data);
    setUserChange(true);
  }

 

  return (
    event && (
      <div className="flex flex-col items-center justify-center">
        <div className="p-6 my-10 border rounded-lg shadow-md bg-light-secondaryBg dark:bg-dark-secondaryBg border-light-border dark:border-dark-border w-full max-w-4xl">
         <EventInfo event={event}/>
          <h2 className="text-2xl font-semibold mt-10">
            {" "}
            Your friends who are coming:{" "}
          </h2>

          <ul className="flex flex-wrap justify-around">
            {event?.users?.map((user) => (
              <li key={user?.id} className="mx-auto">
                <UserCard
                  user={user}
                  onClick={() => handleDeleteUser(event, user)}
                />
              </li>
            ))}
          </ul>

          {event?.tags?.length > 0 && (
            <div className="mt-5">
              <h2 className="text-2xl font-semibold">Tags:</h2>
              <ul className="flex flex-wrap gap-2">
                {event.tags.map((tag) => (
                  <li key={tag.id}>
                    <TagCard tag={tag} color={tag.color} />
                  </li>
                ))}
              </ul>
            </div>
          )}
          {owner === user && (
            <ActionButtons id={eventId} partName={"event"}/>
            
          )}
        </div>
      </div>
    )
  );
}

export default EventDetailPage;
