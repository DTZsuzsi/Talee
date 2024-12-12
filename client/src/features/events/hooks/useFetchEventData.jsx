import { useEffect, useState } from "react";
import axiosInstance from "../../../axiosInstance.jsx";

export function useFetchEventData(eventId) {
  const [event, setEvent] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [owner, setOwner] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const { data } = await axiosInstance.get(`/events/${eventId}`);
        setEvent(data);
        setOwner(data.owner);
      } catch (err) {
        setError(
          err.response?.data?.message ||
            "An error occurred while fetching data.",
        );
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    if (eventId) fetchData();
  }, [eventId, loading]);

  return { event, error, loading, setLoading, owner, setEvent };
}
