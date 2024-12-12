import InputField from "../../main/components/atoms/InputField.jsx";
import SelectField from "../../main/components/atoms/SelectField.jsx";
import TextArea from "../../main/components/atoms/TextArea.jsx";

function EventFormNew({ newEvent, setNewEvent, location }) {
  const sizes = ["SMALL", "MEDIUM", "BIG", "VERY_BIG"];
  const statuses = ["COMING", "IN_PROGRESS"];

  return (
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
      <TextArea
        onChange={(e) =>
          setNewEvent({ ...newEvent, description: e.target.value })
        }
        title="Description"
        placeholder="Enter a detailed description of the event"
        className="col-span-2 w-full"
      />
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
        onChange={(e) => setNewEvent({ ...newEvent, owner: e.target.value })}
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
        onChange={(e) => setNewEvent({ ...newEvent, status: e.target.value })}
      />
    </div>
  );
}

export default EventFormNew;
