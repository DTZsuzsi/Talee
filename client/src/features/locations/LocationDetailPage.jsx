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
        <div className="flex flex-col lg:flex-row gap-6">
          <img
            src="https://images.unsplash.com/photo-1513151233558-d860c5398176?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            alt="Location"
            className="rounded-md w-full lg:w-1/3 object-cover"
          />
          <div className="flex flex-col gap-4 w-full">
            <div>
              <p className="text-2xl font-bold text-center">{location.name}</p>
            </div>
            {location.address && (
              <div>
                <p className="text-lg font-semibold">Address:</p>
                <p className="text-mutedText dark:text-dark-mutedText">
                  {location.address}
                </p>
              </div>
            )}
            {location.description && (
              <div>
                <p className="text-lg font-semibold">Description:</p>
                <p className="text-mutedText dark:text-dark-mutedText">
                  {location.description}
                </p>
              </div>
            )}
            {location.phone && (
              <div>
                <p className="text-lg font-semibold">Phone:</p>
                <p className="text-mutedText dark:text-dark-mutedText">
                  {location.phone}
                </p>
              </div>
            )}
            {location?.openingHours?.length > 0 && (
              <div>
                <p className="text-lg font-semibold">Opening Hours:</p>
                {location.openingHours.map((hour) => (
                  <p
                    key={hour.day}
                    className="text-mutedText dark:text-dark-mutedText"
                  >
                    {hour.day}: {hour.openingTime} - {hour.closingTime}
                  </p>
                ))}
              </div>
            )}
          </div>
        </div>

        {location.latitude && (
          <MapDisplay lat={location.latitude} lng={location.longitude}/>
        )}
        {events.length > 0 && (
          <div>
            <h2 className="text-2xl font-semibold">Events at this location:</h2>
            {events.map((event) => (
              <div key={event.id} className="mt-4">
                <Link
                  to={`/events/${event.id}`}
                  className="block p-4 border rounded-lg shadow-md bg-light-bg dark:bg-dark-bg"
                >
                  <h3 className="text-xl font-bold">{event.name}</h3>
                  <p className="text-mutedText dark:text-dark-mutedText">
                    {event.description}
                  </p>
                </Link>
              </div>
            ))}
          </div>
        )}

        {location?.tags?.length > 0 && (
          <div className="mt-5">
            <h2 className="text-2xl font-semibold">Tags:</h2>
            <ul className="flex flex-wrap gap-2">
              {location.tags.map((tag) => (
                <li key={tag.id}>
                  <TagCard tag={tag} color={tag.color} />
                </li>
              ))}
            </ul>
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
