/* eslint-disable no-unused-vars */
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import TaleeButton from "../main/components/atoms/TaleeButton.jsx";
import Loading from "../main/components/atoms/Loading";
import ServerError from "../main/components/atoms/ServerError";

import LocationForm from "./LocationForm.jsx";
import  useFetchTags  from "../main/components/hooks/useFetchTags.jsx";
import useOpeningHours from "./hooks/useOpeningHours.jsx";
import TagListModify from "../main/components/molecules/TagListModify.jsx";
import axiosInstance from "../../axiosInstance.jsx";
function UpdateLocationForm() {
  const { locationId } = useParams();
  const [location, setLocation] = useState(null);
  const [tagChange, setTagChange] = useState(false);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();
  const { tags } = useFetchTags();
  const { handleOpeningHoursChange } = useOpeningHours(setLocation);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axiosInstance.get(`/locations/${locationId}`);
        console.log(data);
        setLocation(data);
      } catch (err) {
        console.error("Error fetching locations:", err);
      }
    };

    fetchData();
  }, [locationId]);

  async function handleLocationUpdate(e) {
    console.log(location);
    e.preventDefault();
    setLoading(true);
    try {
      await axiosInstance.patch(`/locations`, location);
      navigate(`/locations/${locationId}`);
    } catch (error) {
      console.error("Error updating location:", error);
      setError("Failed to update location");
    } finally {
      setLoading(false);
    }
  }

  if (error) {
    return <ServerError error={error} />;
  }

  return (
    location && (
      <div className="flex justify-center py-10">
        <form
          onSubmit={handleLocationUpdate}
          className="bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl"
        >
          <h1 className="font-bold text-3xl text-center mb-8">
            Update Location
          </h1>

          <LocationForm
            location={location}
            setLocation={setLocation}
            onHoursChange={handleOpeningHoursChange}
          />
          <TagListModify partName={location} setter={setLocation} tags={tags} />
          <div className="w-full flex justify-center mt-6">
            {loading ? (
              <Loading />
            ) : (
              <TaleeButton type="submit" className="mt-5">
                Update Location
              </TaleeButton>
            )}
          </div>
        </form>
      </div>
    )
  );
}

export default UpdateLocationForm;
