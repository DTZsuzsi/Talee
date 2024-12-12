import { useEffect, useState } from "react";
import axiosInstance from "../../../axiosInstance.jsx";

export function useFetchLocationData(locationId) {
  const [location, setLocation] = useState(null);
  const [events, setEvents] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [owner, setOwner] = useState(null);

  useEffect(() => {
    async function fetchData() {
      setLoading(true);
      try {
        const [locationResponse, eventsResponse] = await Promise.all([
          axiosInstance.get(`/locations/${locationId}`),
          axiosInstance.get(`/events/locations/${locationId}`),
        ]);
        setLocation(locationResponse.data);
        setEvents(eventsResponse.data);
        setOwner(locationResponse.data.adminUser);
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

    if (locationId) fetchData();
  }, [locationId]);

  return { location, events, error, loading, owner };
}
