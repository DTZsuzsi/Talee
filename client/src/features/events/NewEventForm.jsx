import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import InputField from '../main/components/atoms/InputField';
import SelectField from '../main/components/atoms/SelectField';

function NewEventForm() {
  const [newEvent, setNewEvent] = useState({
    date: '',
    name: '',
    description: '',
    location_id: 0,
    owner: '',
    size: "SMALL",
    tags: [],
    status: "COMING"
  });
  const navigate = useNavigate();

  const sizes = ["SMALL", "MEDIUM", "BIG", "VERY_BIG"];
  const statuses = ["COMING", "IN_PROGRESS"];

  async function handleNewEvent(e) {
    e.preventDefault();
    const response = await fetch('/api/events', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newEvent),
    });
    if (!response.ok) {
      console.error('Error:', response.status, await response.text());
      return;
    }

    const createdEventId = await response.json();
    console.log('Created event:', createdEventId);
    navigate(`/events/${createdEventId}`);
  }

  return (
    <div>
      <h1 className='font-bold text-3xl mb-5'>New Event</h1>
      <form onSubmit={handleNewEvent} className='flex flex-col items-center'>
        <div>
          <InputField
            label="Date"
            type="date"
            value={newEvent.date}
            onChange={(e) => setNewEvent({ ...newEvent, date: e.target.value })}
          />
          <InputField
            label="Name"
            type="text"
            value={newEvent.name}
            onChange={(e) => setNewEvent({ ...newEvent, name: e.target.value })}
          />
          <InputField
            label="Description"
            type="text"
            value={newEvent.description}
            onChange={(e) => setNewEvent({ ...newEvent, description: e.target.value })}
          />
          <InputField
            label="Location ID"
            type="number"
            value={newEvent.location_id}
            onChange={(e) => setNewEvent({ ...newEvent, location_id: +e.target.value })}
          />
          <InputField
            label="Owner"
            type="text"
            value={newEvent.owner}
            onChange={(e) => setNewEvent({ ...newEvent, owner: e.target.value })}
          />
          <SelectField
            label="Size"
            options={sizes}
            value={newEvent.size || 'SMALL'}
            onChange={(e) => setNewEvent({ ...newEvent, size: e.target.value })}
          />
          <InputField
            label="Tags"
            type="text"
            placeholder="Enter tags separated by commas"
            value={newEvent.tags.join(', ')}
            onChange={(e) => setNewEvent({ ...newEvent, tags: e.target.value.split(',').map(tag => tag.trim()) })}
          />
          <SelectField
            label="Status"
            options={statuses}
            value={newEvent.status || 'COMING'}
            onChange={(e) => setNewEvent({ ...newEvent, status: e.target.value })}
          />
          <button type="submit" className="mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg">
            Create new event
          </button>
        </div>
      </form>
    </div>
  );
}

export default NewEventForm;
