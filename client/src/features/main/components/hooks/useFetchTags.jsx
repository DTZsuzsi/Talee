/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import axios from "axios";
import axiosInstance from "../../../../axiosInstance.jsx";

  function useFetchTags() {
  const [tags, setTags] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  

  useEffect(() => {
    async function fetchData() {
      setLoading(true);
      try {
        const [tagResponse] = await Promise.all([
          axiosInstance.get(`/tags`)
        ]);

        setTags(tagResponse.data);
        
      } catch (err) {
        setError(err.response?.data?.message || "An error occurred while fetching data.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    fetchData();
  }, []);

  return { tags };
}
export default useFetchTags;

