import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import InputField from '../main/components/atoms/InputField.jsx';
import Loading from '../main/components/atoms/Loading.jsx';
import ServerError from '../main/components/atoms/ServerError.jsx';
import GoogleMapComponent from './GoogleMapComponent.jsx';

function NewLocationForm() {
	const [error, setError] = useState(null);
	const [loading, setLoading] = useState(false);
    const [position, setPosition]=useState({lat:0, lng:0});
    const [address, setAddress]=useState('');


	const [newLocation, setNewLocation] = useState({
		name: '',
		address: address,
		phone: '',
		email: '',
		website: '',
		facebook: '',
		instagram: '',
		description: '',
        latitude:position.lat,
        longitude:position.lng,
        openingHours: []
	});


	const navigate = useNavigate();

    useEffect(() => {
        setNewLocation(prev => ({
          ...prev,
          latitude: position.lat,
          longitude: position.lng
        }));
      }, [position]);

    const daysOfWeek = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

    async function handleNewLocation(e) {
        console.log("hi");
        e.preventDefault();
        setLoading(true);

        const newLoc={...newLocation, lat: position.lat, lng: position.lng, address:address};
        console.log(newLoc);
        const response = await fetch('/api/locations', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newLoc)

        const token = localStorage.getItem("jwtToken");

        const response = await fetch('/api/locations', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
             },
            body: JSON.stringify(newLocation)

        });
        if (!response.ok) {
            console.error('Error: ', response.status, await response.text());
            setLoading(false);
            setError('Failed to create location');
            return;
        }

        const createdLocationId = await response.json();
        setLoading(false);
        navigate(`/locations/${createdLocationId}`);
    }

    function handleOpeningHoursChange(dayOfWeek, field, value) {
        setNewLocation((prevLocation) => {
            // Check if the day already exists in openingHours
            let updatedOpeningHours = prevLocation.openingHours || [];
            // Find if there's already an entry for this day
            const existingEntryIndex = updatedOpeningHours.findIndex(hour => hour.day === dayOfWeek);
            if (existingEntryIndex !== -1) {
                // If it exists, update the specified field
                updatedOpeningHours[existingEntryIndex] = {
                    ...updatedOpeningHours[existingEntryIndex],
                    [field]: value
                };
            } else {
                // If not, add a new entry with dayOfWeek and the specified field
                updatedOpeningHours = [
                    ...updatedOpeningHours,
                    { day: dayOfWeek, [field]: value }
                ];
            }

            return { ...prevLocation, openingHours: updatedOpeningHours };

        })
    }
    if (error) {
        return <ServerError error={error} />;
    }

    return (
        <div>
            <h1 className='font-bold text-3xl mb-5'>New Location</h1>
            <form onSubmit={handleNewLocation} className='flex flex-col items-center'>
                <div>
                    <InputField
                        label="Name"
                        type="text"
                        value={newLocation.name}
                        onChange={(e) => setNewLocation({...newLocation, name: e.target.value})}
                    />
                    {/* <InputField
                        label="Address"
                        type="text"
                        value={newLocation.address}
                        onChange={(e) => {setNewLocation({...newLocation, address: e.target.value}); (e)=> setAddress(e.target.value)}
                    }
                    /> */}
                    <InputField
                        label="Phone"
                        type="text"
                        value={newLocation.phone}
                        onChange={(e) => setNewLocation({...newLocation, phone: e.target.value})}
                    />
                    <InputField
                        label="Email"
                        type="text"
                        value={newLocation.email}
                        onChange={(e) => setNewLocation({...newLocation, email: e.target.value})}
                    />
                    <InputField
                        label="Website"
                        type="text"
                        value={newLocation.website}
                        onChange={(e) => setNewLocation({...newLocation, website: e.target.value})}
                    />
                    <InputField
                        label="Facebook"
                        type="text"
                        value={newLocation.facebook}
                        onChange={(e) => setNewLocation({...newLocation, facebook: e.target.value})}
                    />
                    <InputField
                        label="Instagram"
                        type="text"
                        value={newLocation.instagram}
                        onChange={(e) => setNewLocation({...newLocation, instagram: e.target.value})}
                    />
                    <InputField
                        label="Description"
                        type="text"
                        value={newLocation.description}
                        onChange={(e) => setNewLocation({...newLocation, description: e.target.value})}
                    />
                    

                    {daysOfWeek.map((day) => (
                        <div key={day} className="mt-4">
                            <h3 className="font-semibold">{day}</h3>
                            <InputField
                                label="Opening Time"
                                type="time"
                                onChange={(e) => handleOpeningHoursChange(day, "openingTime", e.target.value)}
                            />
                            <InputField
                                label="Closing Time"
                                type="time"
                                onChange={(e) => handleOpeningHoursChange(day, "closingTime", e.target.value)}
                            />
                            
                        </div>
                    ))}
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
            <GoogleMapComponent position={position} setPosition={setPosition} address={address} setAddress={setAddress}/>
        </div>
    );
}

export default NewLocationForm;