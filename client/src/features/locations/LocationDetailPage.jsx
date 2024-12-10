/* eslint-disable no-unused-vars */

import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { HiMiniPencilSquare } from "react-icons/hi2";
import { MdDeleteForever } from "react-icons/md";
import ServerError from "../main/components/atoms/ServerError";
import Loading from "../main/components/atoms/Loading";
import TagCard from "../tag/components/TagCard";
import BiggerOnHover from "../main/components/atoms/BiggerOnHover";
import { APIProvider, Map, Marker } from "@vis.gl/react-google-maps";
import TaleeButton from "../main/components/atoms/TaleeButton.jsx";
import MapDisplay from "../maps/MapDisplay.jsx";
import { useFetchLocationData } from "./hooks/useFetchLocationData.jsx";
import LocationInfo from "./LocationInfo.jsx";
import EventList from "./EventList.jsx";
import TagList from "./TagList.jsx";

function LocationDetailPage() {
  const { locationId } = useParams();
  const navigate = useNavigate();
  const {location, events, error, loading, owner, user}=useFetchLocationData(locationId);
  

  async function deleteLocation() {
    const response = await fetch(`/api/locations/${locationId}`, {
      method: "DELETE",
    });

    if (response.ok) {
      navigate("/");
    } else {
      console.error("Failed to delete location");
    }
  }

  if (error) {
    return <ServerError error={error} />;
  }

  if (!location) {
    return <Loading />;
  }

  return (
    <div className="flex flex-col items-center justify-center">
      <div className="p-6 my-10 border rounded-lg shadow-md bg-light-secondaryBg dark:bg-dark-secondaryBg border-light-border dark:border-dark-border w-full max-w-4xl">
        <LocationInfo location={location}/>

        {location.latitude && (
          <MapDisplay lat={location.latitude} lng={location.longitude}/>
        )}
        {events.length > 0 && (
          <div>
           <EventList events={events}/>
          </div>
        )}

        {location?.tags?.length > 0 && (
          <div className="mt-5">
           <TagList tags={location.tags}/>
          </div>
        )}
        {owner === user && (
          <div className="flex justify-end gap-4 mt-6">
            <BiggerOnHover>
              <Link
                to={`/locations/${locationId}/update`}
                className="flex items-center justify-center w-12 h-12 rounded-full bg-button text-white hover:bg-buttonHover"
              >
                <HiMiniPencilSquare className="w-6 h-6" />
              </Link>
            </BiggerOnHover>

            <BiggerOnHover>
              <button
                onClick={deleteLocation}
                className="flex items-center justify-center w-12 h-12 rounded-full bg-button text-white hover:bg-red-600"
              >
                <MdDeleteForever className="w-6 h-6" />
              </button>
            </BiggerOnHover>

            <Link to={`/events/new/${locationId}`}>
              <TaleeButton> Create event</TaleeButton>
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}

export default LocationDetailPage;
