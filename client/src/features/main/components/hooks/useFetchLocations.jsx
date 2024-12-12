import { useEffect, useState } from "react";
import axios from "axios";

export function useFetchLocations() {
  const [locations, setLocations] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axios.get(`/api/locations/all`);
        setLocations(data);
      } catch (err) {
        console.error("Error fetching locations:", err);
      }
    };

    fetchData();
  }, []);

  return { locations };
}
