import InputField from "../../main/components/atoms/InputField";
import SelectField from "../../main/components/atoms/SelectField";

function EventForm({event, setEvent}){
    const sizes = ["SMALL", "MEDIUM", "BIG", "VERY_BIG"];
    const statuses = ["COMING", "IN_PROGRESS"];
    
    return(
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
      )
}

export default EventForm;