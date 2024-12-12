import TaleeButton from "../../main/components/atoms/TaleeButton.jsx";
import axiosInstance from "../../../axiosInstance.jsx";

function EventJoinButton({ eventId }) {
  async function joinEvent() {
    await axiosInstance.post(`/events/apply/${eventId}`);
  }

  return (
    <div>
      <TaleeButton className="p-10" onClick={joinEvent}>
        Join Event
      </TaleeButton>
    </div>
  );
}

export default EventJoinButton;
