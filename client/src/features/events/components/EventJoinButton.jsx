import TaleeButton from "../../main/components/atoms/TaleeButton.jsx";
import axiosInstance from "../../../axiosInstance.jsx";

function EventJoinButton({ eventId, setLoading }) {
  async function joinEvent() {
    setLoading(true);
    const response = await axiosInstance.post(`/events/apply/${eventId}`);

    if (response.status === 200) {
      setLoading(false);
    }
  }

  return (
    <TaleeButton className="m-3" onClick={joinEvent}>
      Join Event
    </TaleeButton>
  );
}

export default EventJoinButton;
