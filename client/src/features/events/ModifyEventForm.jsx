import { useNavigate, useParams } from "react-router-dom";
import TaleeButton from "../main/components/atoms/TaleeButton.jsx";
import EventForm from "./components/EventForm.jsx";
import TagListModify from "../main/components/molecules/TagListModify.jsx";
import useFetchTags from "../main/components/hooks/useFetchTags.jsx";
import { useFetchEventData } from "./hooks/useFetchEventData.jsx";
import axiosInstance from "../../axiosInstance.jsx";
import UserList from "../main/components/molecules/UserList.jsx";

function ModifyEventForm() {
  const { eventId } = useParams();
  const navigate = useNavigate();
  const { tags } = useFetchTags();
  const { event, setEvent } = useFetchEventData(eventId);

  async function handleModifyingEvent(e) {
    e.preventDefault();

    try {
      const response = await axiosInstance.patch(
        `/events/modify`,
        event,
      );
      navigate(`/events/${eventId}`);
    } catch (error) {
      console.error("Error modifying event:", error);
    }
  }

  function handleDeleteUser(user) {
    setEvent((prevPartName) => ({
      ...prevPartName,
      users: prevPartName.users.filter((e) => e.id !== user.id),
    }));
  }

  return (
    event && (
      <div className="flex justify-center py-10">
        <form
          onSubmit={handleModifyingEvent}
          className="bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl"
        >
          <h1 className="font-bold text-3xl text-center mb-8">Modify Event</h1>

          <EventForm event={event} setEvent={setEvent} />

          <TagListModify partName={event} setter={setEvent} tags={tags} />
          {event.users.length > 0 && (
            <UserList onDeleteUser={handleDeleteUser} event={event} />
          )}

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
