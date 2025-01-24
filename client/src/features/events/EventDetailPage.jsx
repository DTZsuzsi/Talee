/* eslint-disable no-unused-vars */
import { useState } from "react";
import { useParams } from "react-router-dom";
import { useFetchEventData } from "./hooks/useFetchEventData.jsx";
import ActionButtons from "../main/components/molecules/ActionButtons.jsx";
import TagList from "../main/components/molecules/TagList.jsx";
import UserList from "../main/components/molecules/UserList.jsx";
import EventInfo from "./components/EventInfo.jsx";
import Loading from "../main/components/atoms/Loading.jsx";
import { useAuth } from "../auth/AuthContext.jsx";
import EventJoinButton from "./components/EventJoinButton.jsx";

function EventDetailPage() {
  const { eventId } = useParams();

  const { event, error, loading, setLoading, owner } =
      useFetchEventData(eventId);

  const { getUser } = useAuth();
  const user = getUser();

  return loading ? (
      <Loading />
  ) : (
      <div className="flex flex-col items-center justify-center">
        <div className="p-6 my-10 border rounded-lg shadow-md bg-light-secondaryBg dark:bg-dark-secondaryBg border-light-border dark:border-dark-border w-full max-w-4xl">
          <EventInfo event={event} />
          {event.users.length > 0 && <UserList event={event} />}
          {event?.tags?.length > 0 && <TagList tags={event?.tags} />}

          <div className="mt-7 flex justify-between">
            <div className="flex justify-start">
              {owner.username === user.username && (
                  <ActionButtons id={eventId} partName={"event"} />
              )}
            </div>

            <div className="flex items-center justify-end">
              {user?.username ? (
                  event?.users?.some((u) => u.username === user.username) ? (
                      <p className="text-2xl font-semibold text-center">
                        You joined this event!
                      </p>
                  ) : (
                      <EventJoinButton setLoading={setLoading} eventId={eventId} />
                  )
              ) : (
                  ( <p className="text-2xl font-semibold text-center">
                    You need to log in to join this event!
                  </p>
              )

            )}
          </div>
        </div>
      </div>
</div>
);
}

export default EventDetailPage;