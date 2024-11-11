

import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import InputField from '../main/components/atoms/InputField';
import SelectField from '../main/components/atoms/SelectField';

function ModifyEventForm() {
  const { eventId } = useParams();
  const [event, setEvent] = useState(null);  
  const sizes = ['SMALL', 'MEDIUM', 'BIG', 'VERY_BIG'];
  const statuses = ['COMING', 'IN_PROGRESS'];

  const navigate=useNavigate();

  useEffect(() => {
    async function fetchEvent() {
      if (!eventId) return;

      try {
        const response = await fetch(`http://localhost:8080/events/${eventId}`);
        if (!response.ok) throw new Error(`Failed to fetch event: ${response.statusText}`);
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
      const response = await fetch(`http://localhost:8080/events/${eventId}/modify`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(event), 
      });

      if (!response.ok) throw new Error(`Failed to modify event: ${response.statusText}`);

      const data = await response.json();
      console.log("Modified event data:", data); 
     
      navigate(`/events/${eventId}`);

    } catch (error) {
      console.error("Error modifying event:", error);
    }
  }

  return (
    event && (
      <div>
        <h1 className='font-bold text-3xl mb-5'>Modify Event</h1>
        <form onSubmit={handleModifyingEvent} className='flex flex-col items-center'>
          <div>
            {}
            <InputField
              label='Date'
              type='date'
              value={event.date || ''}
              onChange={(e) => setEvent({ ...event, date: e.target.value })}
            />
            <InputField
              label='Name'
              type='text'
              value={event.name || ''}
              onChange={(e) => setEvent({ ...event, name: e.target.value })}
            />
            <InputField
              label='Description'
              type='text'
              value={event.description || ''}
              onChange={(e) => setEvent({ ...event, description: e.target.value })}
            />
            <InputField
              label='Location ID'
              type='number'
              value={event.location_id || ''}
              onChange={(e) => setEvent({ ...event, location_id: +e.target.value })}
            />
            <InputField
              label='Owner'
              type='text'
              value={event.owner || ''}
              onChange={(e) => setEvent({ ...event, owner: e.target.value })}
            />
            <SelectField
              label='Size'
              options={sizes}
              value={event.size || 'SMALL'}
              onChange={(e) => setEvent({ ...event, size: e.target.value })}
            />
            <InputField
              label='Tags'
              type='text'
              placeholder='Enter tags separated by commas'
              value={event.tags ? event.tags.join(', ') : ''}
              onChange={(e) =>
                setEvent({
                  ...event,
                  tags: e.target.value.split(',').map((tag) => tag.trim()),
                })
              }
            />
            <SelectField
              label='Status'
              options={statuses}
              value={event.status || 'COMING'}
              onChange={(e) => setEvent({ ...event, status: e.target.value })}
            />
            <button type='submit' className='mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg'>
              Modify event
            </button>
          </div>
        </form>
      </div>
    )
  );
}

export default ModifyEventForm;
