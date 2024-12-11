import { useEffect, useState } from "react";
import axios from "axios";

export function useFetchEventData(eventId) {
  const [event, setEvent] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [user, setUser]=useState(null);
  const [owner, setOwner]=useState(null);

  useEffect(() => {
    async function fetchData() {
      setLoading(true);
      try {
        const {data} = await  axios.get(`/api/events/${eventId}`);
        setEvent(data);
        setUser(data.adminUser.username);
setOwner(data.owner.username);

        
      } catch (err) {
        setError(err.response?.data?.message || "An error occurred while fetching data.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    if (eventId) fetchData();
  }, [eventId]);

  return { event, error, loading, user, owner };
}