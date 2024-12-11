import { useEffect, useState } from "react";
import axios from "axios";

export function useFetchLocations(id) {
  const [location, setLocation] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axios.get(`/api/locations/${id}`);
        console.log(data)
        setLocation(data);
      } catch (err) {
        console.error("Error fetching locations:", err);
      }
    };

    fetchData();
  }, []); 

  return { location };
}
