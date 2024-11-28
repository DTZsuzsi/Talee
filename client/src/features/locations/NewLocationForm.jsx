import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import InputField from "../main/components/atoms/InputField";
import Button from "../main/components/atoms/Button";
import Loading from "../main/components/atoms/Loading";
import ServerError from "../main/components/atoms/ServerError";
import TagOptions from "../tag/components/TagOptions.jsx";
import TagCard from "../tag/components/TagCard.jsx";

function NewLocationForm() {
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [newLocation, setNewLocation] = useState({
    name: "",
    address: "",
    phone: "",
    email: "",
    website: "",
    facebook: "",
    instagram: "",
    description: "",
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

  async function handleNewLocation(e) {
    e.preventDefault();
    setLoading(true);
    try {
      const token = localStorage.getItem("jwtToken");
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

  const [tags, setTags] = useState(null);

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
    setNewLocation((prevLocation) => {
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
    setNewLocation((prevLocation) => ({
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
            required
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

        <div className="col-span-2 mt-4">
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

        <div className="w-full my-5">
          <TagOptions onChange={(e) => handleNewTag(e)} />
          <ul className="grid grid-cols-2 md:grid-cols-4 gap-4">
            {newLocation.tags?.map((tag) => (
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
              Create Location
            </Button>
          )}
        </div>
      </form>
    </div>
  );
}

export default NewLocationForm;
