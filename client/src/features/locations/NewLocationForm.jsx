import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import InputField from "../main/components/atoms/InputField.jsx";
import TaleeButton from "../main/components/atoms/TaleeButton.jsx";
import Loading from "../main/components/atoms/Loading.jsx";
import ServerError from "../main/components/atoms/ServerError.jsx";
import GoogleMapComponent from "../maps/GoogleMapComponent.jsx";
import TagOptions from "../tag/components/TagOptions.jsx";
import TagCard from "../tag/components/TagCard.jsx";
import { useFetchTags } from "./hooks/useFetchTags.jsx";

function NewLocationForm() {
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [position, setPosition] = useState({ lat: 0, lng: 0 });
  const [address, setAddress] = useState("");

  const [newLocation, setNewLocation] = useState({
    name: "",
    address: address,
    phone: "",
    email: "",
    website: "",
    facebook: "",
    instagram: "",
    description: "",
    latitude: position.lat,
    longitude: position.lng,
    openingHours: [],
    tags: [],
  });

  const navigate = useNavigate();

  const daysOfWeek = [
    "MONDAY",
    "TUESDAY",
    "WEDNESDAY",
    "THURSDAY",
    "FRIDAY",
    "SATURDAY",
    "SUNDAY",
  ];

  const {tags}=useFetchTags;

  // Update coordinates when position changes
  useEffect(() => {
    setNewLocation((prev) => ({
      ...prev,
      latitude: position.lat,
      longitude: position.lng,
    }));
  }, [position]);

  function handleOpeningHoursChange(day, field, value) {
    setNewLocation((prevLocation) => {
      const updatedHours = prevLocation.openingHours || [];
      const existingDayIndex = updatedHours.findIndex(
        (hour) => hour.day === day,
      );

      if (existingDayIndex !== -1) {
        updatedHours[existingDayIndex] = {
          ...updatedHours[existingDayIndex],
          [field]: value,
        };
      } else {
        updatedHours.push({ day, [field]: value });
      }

      return { ...prevLocation, openingHours: updatedHours };
    });
  }

  function handleNewTag(e) {
    const selectedTagName = e.target.value;
    const tagToAdd = tags?.find((tag) => tag.name === selectedTagName);

    if (!tagToAdd) {
      console.warn(`Tag with name ${selectedTagName} not found.`);
      return;
    }

    if (newLocation.tags.some((tag) => tag.id === tagToAdd.id)) {
      console.warn(`Tag with ID ${tagToAdd.id} is already added.`);
      return;
    }

    setNewLocation((prevLocation) => ({
      ...prevLocation,
      tags: [...prevLocation.tags, tagToAdd],
    }));
  }

  function handleDeleteTag(tag) {
    setNewLocation((prevLocation) => ({
      ...prevLocation,
      tags: prevLocation.tags.filter((t) => t.id !== tag.id),
    }));
  }

  async function handleNewLocation(e) {
    e.preventDefault();
    setLoading(true);
    const token = localStorage.getItem("jwtToken");

    try {
      const response = await fetch("/api/locations", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(newLocation),
      });

      if (!response.ok) throw new Error("Failed to create location");

      const createdLocationId = await response.json();
      navigate(`/locations/${createdLocationId}`);
    } catch (error) {
      console.error("Error creating location:", error);
      setError("Failed to create location");
    } finally {
      setLoading(false);
    }
  }

  if (error) {
    return <ServerError error={error} />;
  }

  return (
    <div className="flex justify-center py-10">
      <form
        onSubmit={handleNewLocation}
        className="bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl"
      >
        <h1 className="font-bold text-3xl text-center mb-8">
          Create New Location
        </h1>

        {/* Basic Information */}
        <div className="grid grid-cols-2 gap-4">
          <InputField
            label="Name"
            type="text"
            value={newLocation.name}
            onChange={(e) =>
              setNewLocation({ ...newLocation, name: e.target.value })
            }
            required
          />
          <InputField
            label="Address"
            type="text"
            value={newLocation.address}
            onChange={(e) =>
              setNewLocation({ ...newLocation, address: e.target.value })
            }
          />
          <InputField
            label="Phone"
            type="text"
            value={newLocation.phone}
            onChange={(e) =>
              setNewLocation({ ...newLocation, phone: e.target.value })
            }
          />
          <InputField
            label="Email"
            type="email"
            value={newLocation.email}
            onChange={(e) =>
              setNewLocation({ ...newLocation, email: e.target.value })
            }
          />
          <InputField
            label="Website"
            type="url"
            value={newLocation.website}
            onChange={(e) =>
              setNewLocation({ ...newLocation, website: e.target.value })
            }
          />
          <InputField
            label="Facebook"
            type="text"
            value={newLocation.facebook}
            onChange={(e) =>
              setNewLocation({ ...newLocation, facebook: e.target.value })
            }
          />
          <InputField
            label="Instagram"
            type="text"
            value={newLocation.instagram}
            onChange={(e) =>
              setNewLocation({ ...newLocation, instagram: e.target.value })
            }
          />
        </div>

        {/* Description */}
        <div className="mt-6">
          <label className="block font-medium mb-2">Description</label>
          <textarea
            rows="4"
            value={newLocation.description}
            onChange={(e) =>
              setNewLocation({ ...newLocation, description: e.target.value })
            }
            className="block w-full rounded-md border"
            placeholder="Enter a description"
          ></textarea>
        </div>

        {/* Opening Hours */}
        <div className="mt-6">
          <h2 className="font-bold text-xl mb-4">Opening Hours</h2>
          {daysOfWeek.map((day) => (
            <div key={day} className="grid grid-cols-3 gap-4 mb-2">
              <span className="font-semibold">{day}</span>
              <InputField
                label="Opening Time"
                type="time"
                onChange={(e) =>
                  handleOpeningHoursChange(day, "openingTime", e.target.value)
                }
              />
              <InputField
                label="Closing Time"
                type="time"
                onChange={(e) =>
                  handleOpeningHoursChange(day, "closingTime", e.target.value)
                }
              />
            </div>
          ))}
        </div>

        {/* Tags */}
        <div className="mt-6">
          <TagOptions onChange={handleNewTag} />
          <ul className="grid grid-cols-2 md:grid-cols-4 gap-4">
            {newLocation.tags.map((tag) => (
              <li key={tag.id}>
                <TagCard
                  tag={tag}
                  onClick={() => handleDeleteTag(tag)}
                  color={tag.color}
                  className="w-full"
                />
              </li>
            ))}
          </ul>
        </div>

        {/* Map */}
        <div className="mt-6">
          <GoogleMapComponent
            position={position}
            setPosition={setPosition}
            address={address}
            setAddress={setAddress}
          />
        </div>

        {/* Submit Button */}
        <div className="w-full flex justify-center mt-6">
          {loading ? (
            <Loading />
          ) : (
            <TaleeButton type="submit" className="mt-5">
              Create Location
            </TaleeButton>
          )}
        </div>
      </form>
    </div>
  );
}

export default NewLocationForm;
