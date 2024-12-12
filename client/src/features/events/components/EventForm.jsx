import InputField from "../../main/components/atoms/InputField";
import SelectField from "../../main/components/atoms/SelectField";
import TextArea from "../../main/components/atoms/TextArea.jsx";

function EventForm({ event, setEvent }) {
  const sizes = ["SMALL", "MEDIUM", "BIG", "VERY_BIG"];
  const statuses = ["COMING", "IN_PROGRESS"];

  return (
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
      <TextArea
        onChange={(e) => setEvent({ ...event, description: e.target.value })}
        title="Description"
        value={event.description || ""}
        placeholder="Enter a detailed description of the event"
        className="col-span-2 w-full"
      />
    </div>
  );
}

export default EventForm;
