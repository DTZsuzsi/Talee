import { useEffect, useState } from "react";
import axios from "axios";

export function useFetchLocationData(locationId) {
  const [location, setLocation] = useState(null);
  const [events, setEvents] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [owner, setOwner] = useState(null);
  const [user, setUser] = useState(null);

  useEffect(() => {
    async function fetchData() {
      setLoading(true);
      try {
        const [locationResponse, eventsResponse] = await Promise.all([
          axios.get(`/api/locations/${locationId}`),
          axios.get(`/api/events/locations/${locationId}`),
        ]);

        setLocation(locationResponse.data);
        setEvents(eventsResponse.data);
        setUser(locationResponse.data.adminUser.username);
        setOwner(localStorage.getItem("userName"));
      } catch (err) {
        setError(err.response?.data?.message || "An error occurred while fetching data.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    if (locationId) fetchData();
  }, [locationId]);
  console.log(location);

  return { location, events, error, loading, owner, user };
}