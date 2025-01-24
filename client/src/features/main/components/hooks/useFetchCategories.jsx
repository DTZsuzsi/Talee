/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import axiosInstance from "../../../../axiosInstance.jsx";

function useFetchCategories() {
    const [categories, setCategories] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);


    useEffect(() => {
        async function fetchData() {
            setLoading(true);
            try {
                const [tagResponse] = await Promise.all([
                    axiosInstance.get(`/tagcategories`)
                ]);

                setCategories(tagResponse.data);

            } catch (err) {
                setError(err.response?.data?.message || "An error occurred while fetching data.");
                console.error(err);
            } finally {
                setLoading(false);
            }
        }

        fetchData();
    }, []);

    return { categories };
}
export default useFetchCategories;

