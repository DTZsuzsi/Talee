import { useEffect, useState } from "react";
import axios from "axios";

export function useFetchEvents() {
  const [events, setEvents] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axios.get(`/api/events/all`);
        setEvents(data);
      } catch (err) {
        console.error("Error fetching locations:", err);
      }
    };

    fetchData();
  }, []); 

  return { events };
}


