import { useEffect, useState } from "react";
import axios from "axios";
import axiosInstance from "../../../../axiosInstance.jsx";

export function useFetchEvents() {
  const [events, setEvents] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axiosInstance.get(`/events/all`);
        setEvents(data);
      } catch (err) {
        console.error("Error fetching locations:", err);
      }
    };

    fetchData();
  }, []); 

  return { events };
}


