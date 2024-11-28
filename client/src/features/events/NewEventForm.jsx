import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import InputField from "../main/components/atoms/InputField";
import SelectField from "../main/components/atoms/SelectField";
import Button from "../main/components/atoms/Button";
import ServerError from "../main/components/atoms/ServerError";
import Loading from "../main/components/atoms/Loading";

function NewEventForm() {
  const { locationId } = useParams();
  const [location, setLocation] = useState(null);
  const [newEvent, setNewEvent] = useState({
    date: "",
    name: "",
    description: "",
    locationInEventDTO: { locationId: locationId, name: location?.name },
    owner: "",
    size: "SMALL",
    status: "COMING",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const navigate = useNavigate();

  const sizes = ["SMALL", "MEDIUM", "BIG", "VERY_BIG"];
  const statuses = ["COMING", "IN_PROGRESS"];

  useEffect(() => {
    async function fetchLocation() {
      try {
        const response = await fetch(`/api/locations/${locationId}`);
        if (!response.ok) throw new Error("Failed to fetch location");
        const data = await response.json();
        setLocation(data);
        setNewEvent((prevEvent) => ({
          ...prevEvent,
          locationInEventDTO: {
            locationId: parseInt(locationId, 10),
            name: data.name,
          },
        }));
      } catch (error) {
        console.error("Error fetching location:", error);
        setError("Failed to fetch location");
      }
    }
    fetchLocation();
  }, [locationId]);

  async function handleNewEvent(e) {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await fetch("/api/events", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newEvent),
      });

      if (!response.ok) throw new Error(`Failed to create event`);

      const createdEventId = await response.json();
      navigate(`/events/${createdEventId}`);
    } catch (error) {
      console.error("Error creating event:", error);
      setError("Failed to create event");
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
        onSubmit={handleNewEvent}
        className="bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl"
      >
        <h1 className="font-bold text-3xl text-center mb-8">
          Create New Event
        </h1>

        <div className="grid grid-cols-2 gap-4">
          {/* Event Name */}
          <InputField
            label="Name"
            type="text"
            value={newEvent.name}
            onChange={(e) => setNewEvent({ ...newEvent, name: e.target.value })}
            required
          />
          {/* Event Date */}
          <InputField
            label="Date"
            type="date"
            value={newEvent.date}
            onChange={(e) => setNewEvent({ ...newEvent, date: e.target.value })}
            required
          />
          {/* Description */}
          <div className="col-span-2">
            <label htmlFor="description" className="block font-medium mb-2">
              Description
            </label>
            <textarea
              id="description"
              rows="4"
              value={newEvent.description}
              onChange={(e) =>
                setNewEvent({ ...newEvent, description: e.target.value })
              }
              className="block w-full rounded-md border"
              placeholder="Enter a detailed description of the event"
            ></textarea>
          </div>
          {/* Location */}
          <InputField
            label="Location"
            type="text"
            value={location?.name || ""}
            readOnly
          />
          {/* Owner */}
          <InputField
            label="Owner"
            type="text"
            value={newEvent.owner}
            onChange={(e) =>
              setNewEvent({ ...newEvent, owner: e.target.value })
            }
          />
          {/* Size Selector */}
          <SelectField
            label="Size"
            options={sizes}
            value={newEvent.size}
            onChange={(e) => setNewEvent({ ...newEvent, size: e.target.value })}
          />
          {/* Status Selector */}
          <SelectField
            label="Status"
            options={statuses}
            value={newEvent.status}
            onChange={(e) =>
              setNewEvent({ ...newEvent, status: e.target.value })
            }
          />
        </div>

        <div className="w-full flex justify-center">
          {loading ? (
            <Loading />
          ) : (
            <Button type="submit" className="mt-5">
              Create Event
            </Button>
          )}
        </div>
      </form>
    </div>
  );
}

export default NewEventForm;
