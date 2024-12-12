import InputField from "../../main/components/atoms/InputField.jsx";
import SelectField from "../../main/components/atoms/SelectField.jsx";

function EventFormNew({newEvent, setNewEvent, location}) {
    const sizes = ["SMALL", "MEDIUM", "BIG", "VERY_BIG"];
    const statuses = ["COMING", "IN_PROGRESS"];

    return (<div className="grid grid-cols-2 gap-4">
        {/* Event Name */}
        <InputField
            label="Name"
            type="text"
            value={newEvent.name}
            onChange={(e) => setNewEvent({...newEvent, name: e.target.value})}
            required
        />
        {/* Event Date */}
        <InputField
            label="Date"
            type="date"
            value={newEvent.date}
            onChange={(e) => setNewEvent({...newEvent, date: e.target.value})}
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
                    setNewEvent({...newEvent, description: e.target.value})
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
                setNewEvent({...newEvent, owner: e.target.value})
            }
        />
        {/* Size Selector */}
        <SelectField
            label="Size"
            options={sizes}
            value={newEvent.size}
            onChange={(e) => setNewEvent({...newEvent, size: e.target.value})}
        />
        {/* Status Selector */}
        <SelectField
            label="Status"
            options={statuses}
            value={newEvent.status}
            onChange={(e) =>
                setNewEvent({...newEvent, status: e.target.value})
            }
        />
    </div>)
}

export default EventFormNew;