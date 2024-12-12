import { useEffect, useState } from "react";
import axiosInstance from "../../../../axiosInstance.jsx";

export function useFetchLocations() {
  const [locations, setLocations] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axiosInstance.get(`/locations/all`);
        console.log(data)
        setLocations(data);
      } catch (err) {
        console.error("Error fetching locations:", err);
      }
    };

    fetchData();
  }, []); 

  return { locations };
}


