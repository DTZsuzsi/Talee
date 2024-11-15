import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import InputField from '../main/components/atoms/InputField.jsx';
import Loading from '../main/components/atoms/Loading.jsx';
import ServerError from '../main/components/atoms/ServerError.jsx';

function NewLocationForm() {
	const [error, setError] = useState(null);
	const [loading, setLoading] = useState(false);
	const [newLocation, setNewLocation] = useState({
		name: '',
		address: '',
		phone: '',
		email: '',
		website: '',
		facebook: '',
		instagram: '',
		description: '',
	});

	const navigate = useNavigate();

	async function handleNewLocation(e) {
		e.preventDefault();
		setLoading(true);
		const response = await fetch('/api/locations', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(newLocation),
		});
		if (!response.ok) {
			console.error('Error: ', response.status, await response.text());
			setLoading(false);
			setError('Failed to create location');
			return;
		}

		const createdLocationId = await response.json();
		console.log('Created location: ', createdLocationId);
		setLoading(false);
		//TODO implement location details page
		navigate(`/locations/${createdLocationId}`);
	}
	if (error) {
		return <ServerError error={error} />;
	}

	return (
		<div>
			<h1 className='font-bold text-3xl mb-5'>New Location</h1>
			<form
				onSubmit={handleNewLocation}
				className='flex flex-col items-center'
			>
				<div>
					<InputField
						label='Name'
						type='text'
						value={newLocation.name}
						onChange={e =>
							setNewLocation({ ...newLocation, name: e.target.value })
						}
					/>
					<InputField
						label='Address'
						type='text'
						value={newLocation.address}
						onChange={e =>
							setNewLocation({ ...newLocation, address: e.target.value })
						}
					/>
					<InputField
						label='Phone'
						type='text'
						value={newLocation.phone}
						onChange={e =>
							setNewLocation({ ...newLocation, phone: e.target.value })
						}
					/>
					<InputField
						label='Email'
						type='text'
						value={newLocation.email}
						onChange={e =>
							setNewLocation({ ...newLocation, email: e.target.value })
						}
					/>
					<InputField
						label='Website'
						type='text'
						value={newLocation.website}
						onChange={e =>
							setNewLocation({ ...newLocation, website: e.target.value })
						}
					/>
					<InputField
						label='Facebook'
						type='text'
						value={newLocation.facebook}
						onChange={e =>
							setNewLocation({ ...newLocation, facebook: e.target.value })
						}
					/>
					<InputField
						label='Instagram'
						type='text'
						value={newLocation.instagram}
						onChange={e =>
							setNewLocation({ ...newLocation, instagram: e.target.value })
						}
					/>
					<InputField
						label='Description'
						type='text'
						value={newLocation.description}
						onChange={e =>
							setNewLocation({ ...newLocation, description: e.target.value })
						}
					/>
					{loading ? (
						<Loading />
					) : (
						<button
							type='submit'
							className='mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg'
						>
							Create New Location
						</button>
					)}
				</div>
			</form>
		</div>
	);
}

export default NewLocationForm;
