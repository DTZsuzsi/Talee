import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { HiMiniPencilSquare } from "react-icons/hi2";
import { MdDeleteForever } from "react-icons/md";
import TagCard from "../tag/components/TagCard";
import BiggerOnHover from "../main/components/atoms/BiggerOnHover.jsx";
import UserCard from "../users/components/UserCard.jsx";

function EventDetailPage() {
  const { eventId } = useParams();
  const [event, setEvent] = useState(null);
  const [userChange, setUserChange] = useState(false);
  const navigate = useNavigate();
  const [owner, setOwner] = useState(null);
  const [user, setUser] = useState(null);

  useEffect(() => {
    async function fetchEvent() {
      if (!eventId) {
        console.error("Event ID is undefined");
        return;
      }

      const response = await fetch(`/api/events/${eventId}`);
      if (response.ok) {
        const data = await response.json();
        setEvent(data);
        setOwner(localStorage.getItem("userName"));
        setUser(data.owner.username);
      }
    }

    fetchEvent();
  }, [eventId, userChange]);

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

  async function deleteEvent() {
    const token = localStorage.getItem("jwtToken");
    const response = await fetch(`/api/events/${eventId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    });

    if (response.ok) {
      console.log("Event deleted");
      navigate("/");
    } else {
      console.error("Failed to delete event");
    }
  }

  return (
    event && (
      <div className="flex flex-col items-center justify-center">
        <div className="p-6 my-10 border rounded-lg shadow-md bg-light-secondaryBg dark:bg-dark-secondaryBg border-light-border dark:border-dark-border w-full max-w-4xl">
          <div className="flex flex-col lg:flex-row gap-6">
            <img
              src="https://images.unsplash.com/photo-1513151233558-d860c5398176?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="Event"
              className="rounded-md w-full lg:w-1/3 object-cover"
            />
            <div className="flex flex-col gap-4 w-full">
              <div>
                <p className="text-2xl font-bold text-center">{event.name}</p>
              </div>
              <div>
                <p className="text-lg font-semibold">Date:</p>
                <p className="text-mutedText dark:text-dark-mutedText">
                  {event.date}
                </p>
              </div>
              <div>
                <p className="text-lg font-semibold">Description:</p>
                <p className="text-mutedText dark:text-dark-mutedText">
                  {event.description}
                </p>
              </div>
              <div>
                <p className="text-lg font-semibold">Owner:</p>
                <p className="text-mutedText dark:text-dark-mutedText">
                  {event.owner.username}
                </p>
              </div>

              <div className="mt-4">
                <p className="text-lg font-semibold">Events Location:</p>
                <BiggerOnHover>
                  <Link
                    to={`/locations/${event.location.locationId}`}
                    className="block p-4 border rounded-lg shadow-md bg-light-bg dark:bg-dark-bg"
                  >
                    <h3 className="text-xl font-bold">{event.location.name}</h3>
                    <p className="text-mutedText dark:text-dark-mutedText">
                      {event.location.description}
                    </p>
                  </Link>
                </BiggerOnHover>
              </div>
            </div>
          </div>
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
            <div className="flex justify-end gap-4 mt-6">
              <BiggerOnHover>
                <Link
                  to={`/events/${eventId}/modify`}
                  className="flex items-center justify-center w-12 h-12 rounded-full bg-button text-white hover:bg-buttonHover"
                >
                  <HiMiniPencilSquare className="w-6 h-6" />
                </Link>
              </BiggerOnHover>

              <BiggerOnHover>
                <button
                  onClick={deleteEvent}
                  className="flex items-center justify-center w-12 h-12 rounded-full bg-button text-white hover:bg-red-600"
                >
                  <MdDeleteForever className="w-6 h-6" />
                </button>
              </BiggerOnHover>
            </div>
          )}
        </div>
      </div>
    )
  );
}

export default EventDetailPage;
