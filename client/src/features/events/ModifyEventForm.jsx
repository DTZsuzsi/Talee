import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import InputField from "../main/components/atoms/InputField";
import SelectField from "../main/components/atoms/SelectField";
import Button from "../main/components/atoms/Button.jsx";

function ModifyEventForm() {
  const { eventId } = useParams();
  const [event, setEvent] = useState(null);
  const sizes = ["SMALL", "MEDIUM", "BIG", "VERY_BIG"];
  const statuses = ["COMING", "IN_PROGRESS"];

  const navigate = useNavigate();

  useEffect(() => {
    async function fetchEvent() {
      if (!eventId) return;

      try {
        const response = await fetch(`/api/events/${eventId}`);
        if (!response.ok)
          throw new Error(`Failed to fetch event: ${response.statusText}`);
        const data = await response.json();
        setEvent(data);
      } catch (error) {
        console.error("Error fetching event:", error);
      }
    }
    fetchEvent();
  }, [eventId]);

  async function handleModifyingEvent(e) {
    e.preventDefault();
    console.log("Submitting event:", event);

    try {
      const response = await fetch(
        `http://localhost:8080/events/${eventId}/modify`,
        {
          method: "PATCH",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(event),
        },
      );

      if (!response.ok)
        throw new Error(`Failed to modify event: ${response.statusText}`);

      const data = await response.json();
      console.log("Modified event data:", data);

      navigate(`/events/${eventId}`);
    } catch (error) {
      console.error("Error modifying event:", error);
    }
  }

  return (
    event && (
      <div className="flex justify-center py-10">
        <form
          onSubmit={handleModifyingEvent}
          className="bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl"
        >
          <h1 className="font-bold text-3xl text-center mb-8">Modify Event</h1>

          <div className="grid grid-cols-2 gap-4">
            <InputField
              label="Name"
              type="text"
              value={event.name || ""}
              onChange={(e) => setEvent({ ...event, name: e.target.value })}
            />
            <InputField
              label="Date"
              type="date"
              value={event.date || ""}
              onChange={(e) => setEvent({ ...event, date: e.target.value })}
            />
            <div className="col-span-2">
              <label htmlFor="description" className="block font-medium mb-2">
                Description
              </label>
              <textarea
                id="description"
                rows="4"
                value={event.description || ""}
                onChange={(e) =>
                  setEvent({ ...event, description: e.target.value })
                }
                className="block w-full rounded-md border"
                placeholder="Enter a detailed description of the event"
              ></textarea>
            </div>
            <InputField
              label="Location ID"
              type="number"
              value={event.location_id || ""}
              onChange={(e) =>
                setEvent({ ...event, location_id: +e.target.value })
              }
            />
            <InputField
              label="Owner"
              type="text"
              value={event.owner || ""}
              onChange={(e) => setEvent({ ...event, owner: e.target.value })}
            />
            <SelectField
              label="Size"
              options={sizes}
              value={event.size || "SMALL"}
              onChange={(e) => setEvent({ ...event, size: e.target.value })}
            />
            <SelectField
              label="Status"
              options={statuses}
              value={event.status || "COMING"}
              onChange={(e) => setEvent({ ...event, status: e.target.value })}
            />
          </div>

          <div className="w-full flex justify-center py-4">
            <Button type="submit" className="mt-5">
              Modify Event
            </Button>
          </div>
        </form>
      </div>
    )
  );
}

export default ModifyEventForm;
