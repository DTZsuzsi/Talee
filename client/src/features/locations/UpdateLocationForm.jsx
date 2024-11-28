import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import InputField from "../main/components/atoms/InputField";
import Button from "../main/components/atoms/Button";
import Loading from "../main/components/atoms/Loading";
import ServerError from "../main/components/atoms/ServerError";
import TagOptions from "../tag/components/TagOptions.jsx";
import TagCard from "../tag/components/TagCard.jsx";

function UpdateLocationForm() {
  const { locationId } = useParams();
  const [location, setLocation] = useState(null);
  const [tags, setTags] = useState(null);
  const [tagChange, setTagChange] = useState(false);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

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

  const token = localStorage.getItem("jwtToken");

  // Fetch location details
  useEffect(() => {
    async function fetchLocation() {
      try {
        const response = await fetch(`/api/locations/${locationId}`);
        if (!response.ok) throw new Error(`Failed to fetch location`);
        const data = await response.json();
        setLocation(data);
      } catch (error) {
        console.error("Error fetching location:", error);
        setError("Failed to fetch location");
      }
    }
    fetchLocation();
  }, [locationId, tagChange]);

  // Handle opening hours change
  function handleOpeningHoursChange(day, field, value) {
    setLocation((prevLocation) => {
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

  useEffect(() => {
    async function fetchTags() {
      const response = await fetch("/api/tags");
      const data = await response.json();
      setTags(data);
    }

    fetchTags();
  }, []);

  function handleNewTag(e) {
    const selectedTagName = e.target.value;
    const tagToAdd = findTag(selectedTagName);

    if (!tagToAdd) {
      console.warn(`Tag with name ${selectedTagName} not found.`);
      return;
    }

    // Add the tag to the `newEvent.tags` if it's not already present
    setLocation((prevLocation) => {
      const isTagAlreadyAdded = prevLocation.tags.includes(tagToAdd);

      if (isTagAlreadyAdded) {
        console.warn(`Tag with ID ${tagToAdd.id} is already added.`);
        return prevLocation; // No changes if the tag is already in the array
      }

      return {
        ...prevLocation,
        tags: [...prevLocation.tags, tagToAdd],
      };
    });
  }

  function handleDeleteTag(tag) {
    // Remove the tag from `newEvent.tags`
    setLocation((prevLocation) => ({
      ...prevLocation,
      tags: prevLocation.tags.filter(
        (existingTag) => existingTag.id !== tag.id,
      ),
    }));
  }

  function findTag(tagName) {
    if (!tags) {
      console.warn("Tags not loaded yet.");
      return null;
    }

    // Find the tag by name from the `tags` state
    return tags.find((tag) => tag.name === tagName) || null;
  }

  // Handle location update submission
  async function handleLocationUpdate(e) {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await fetch(`/api/locations/${locationId}`, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(location),
      });
      if (!response.ok) throw new Error("Failed to update location");
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

          <div className="grid grid-cols-2 gap-4">
            <InputField
              label="Name"
              type="text"
              value={location.name || ""}
              onChange={(e) =>
                setLocation({ ...location, name: e.target.value })
              }
            />
            <InputField
              label="Address"
              type="text"
              value={location.address || ""}
              onChange={(e) =>
                setLocation({ ...location, address: e.target.value })
              }
            />
            <InputField
              label="Phone"
              type="text"
              value={location.phone || ""}
              onChange={(e) =>
                setLocation({ ...location, phone: e.target.value })
              }
            />
            <InputField
              label="Email"
              type="email"
              value={location.email || ""}
              onChange={(e) =>
                setLocation({ ...location, email: e.target.value })
              }
            />
            <InputField
              label="Website"
              type="url"
              value={location.website || ""}
              onChange={(e) =>
                setLocation({ ...location, website: e.target.value })
              }
            />
            <InputField
              label="Facebook"
              type="text"
              value={location.facebook || ""}
              onChange={(e) =>
                setLocation({ ...location, facebook: e.target.value })
              }
            />
            <InputField
              label="Instagram"
              type="text"
              value={location.instagram || ""}
              onChange={(e) =>
                setLocation({ ...location, instagram: e.target.value })
              }
            />
          </div>

          <div className="mt-6">
            <label className="block font-medium mb-2">Description</label>
            <textarea
              rows="4"
              value={location.description || ""}
              onChange={(e) =>
                setLocation({ ...location, description: e.target.value })
              }
              className="block w-full rounded-md border"
              placeholder="Enter a description"
            ></textarea>
          </div>

          <div className="mt-6">
            <h2 className="font-bold text-xl mb-4">Opening Hours</h2>
            {daysOfWeek.map((day) => (
              <div key={day} className="grid grid-cols-3 gap-4 mb-2">
                <span className="font-semibold">{day}</span>
                <InputField
                  label="Opening Time"
                  type="time"
                  value={
                    location.openingHours?.find((hour) => hour.day === day)
                      ?.openingTime || ""
                  }
                  onChange={(e) =>
                    handleOpeningHoursChange(day, "openingTime", e.target.value)
                  }
                />
                <InputField
                  label="Closing Time"
                  type="time"
                  value={
                    location.openingHours?.find((hour) => hour.day === day)
                      ?.closingTime || ""
                  }
                  onChange={(e) =>
                    handleOpeningHoursChange(day, "closingTime", e.target.value)
                  }
                />
              </div>
            ))}
          </div>

          <div className="w-full my-5">
            <TagOptions onChange={handleNewTag} />
            <ul className="grid grid-cols-2 md:grid-cols-4 gap-4">
              {location.tags?.map((tag) => (
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

          <div className="w-full flex justify-center mt-6">
            {loading ? (
              <Loading />
            ) : (
              <Button type="submit" className="mt-5">
                Update Location
              </Button>
            )}
          </div>
        </form>
      </div>
    )
  );
}

export default UpdateLocationForm;
