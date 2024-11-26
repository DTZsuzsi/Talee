import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import InputField from '../main/components/atoms/InputField';
import SelectField from '../main/components/atoms/SelectField';
import ServerError from '../main/components/atoms/ServerError';
import Loading from '../main/components/atoms/Loading';

function NewEventForm() {
	const [error, setError] = useState(null);
	const [loading, setLoading] = useState(false);
	const [location, setLocation]=useState(null);
	const {locationId}=useParams();
	const [newEvent, setNewEvent] = useState({
		date: '',
		name: '',
		description: '',
		locationInEventDTO: {locationId:locationId, name: location?.name},
		owner: '',
		size: 'SMALL',
		tags: [],
		status: 'COMING',
	});
	const navigate = useNavigate();

	useEffect(()=>{
async function fetchLocation(){
	const response= await fetch(`/api/locations/${locationId}`);
	const data= await response.json();
	setLocation(data);
	setNewEvent(prevEvent => ({
        ...prevEvent,
        locationInEventDTO: {locationId: parseInt(locationId,10), name: data.name },
      }));
}

fetchLocation();
	}, [locationId])

	const sizes = ['SMALL', 'MEDIUM', 'BIG', 'VERY_BIG'];
	const statuses = ['COMING', 'IN_PROGRESS'];

	async function handleNewEvent(e) {
		e.preventDefault();
		setLoading(true);
		const response = await fetch('/api/events', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(newEvent),
		});
		console.log(newEvent);
		if (!response.ok) {
			console.error('Error:', response.status, await response.text());
			setLoading(false);
			setError('Failed to create event');
			return;
		}

		const createdEventId = await response.json();
		setLoading(false);
		console.log('Created event:', createdEventId);
		navigate(`/events/${createdEventId}`);
	}
	if (error) {
		return <ServerError error={error} />;
	}

	return (
		<div>
			<h1 className='font-bold text-3xl mb-5'>New Event</h1>
			<form
				onSubmit={handleNewEvent}
				className='flex flex-col items-center'
			>
				<div>
					<InputField
						label='Date'
						type='date'
						value={newEvent.date}
						onChange={e => setNewEvent({ ...newEvent, date: e.target.value })}
					/>
					<InputField
						label='Name'
						type='text'
						value={newEvent.name}
						onChange={e => setNewEvent({ ...newEvent, name: e.target.value })}
					/>
					<InputField
						label='Description'
						type='text'
						value={newEvent.description}
						onChange={e =>
							setNewEvent({ ...newEvent, description: e.target.value })
						}
					/>
					<InputField
						label='Location'
						type='text'
						value={location?.name}
						
						
		
					/>
					<InputField
						label='Owner'
						type='text'
						value={newEvent.owner}
						onChange={e => setNewEvent({ ...newEvent, owner: e.target.value })}
					/>
					<SelectField
						label='Size'
						options={sizes}
						value={newEvent.size || 'SMALL'}
						onChange={e => setNewEvent({ ...newEvent, size: e.target.value })}
					/>
					
					<SelectField
						label='Status'
						options={statuses}
						value={newEvent.status || 'COMING'}
						onChange={e => setNewEvent({ ...newEvent, status: e.target.value })}
					/>
					{loading ? (
						<Loading />
					) : (
						<button
							type='submit'
							className='mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg'
						>
							Create new event
						</button>
					)}
				</div>
			</form>
		</div>
	);
}

export default NewEventForm;
