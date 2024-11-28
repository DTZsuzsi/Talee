import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import InputField from "../main/components/atoms/InputField.jsx";


function UpdateLocationForm() {
    const { locationId } = useParams();
    const [location, setLocation] = useState(null);

    const navigate = useNavigate();

    const daysOfWeek = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

    useEffect(() => {
        async function fetchLocation() {
            if (!locationId) return;

            try {
                const response = await fetch(`/api/locations/${locationId}`);
                if (!response.ok) throw new Error(`Failed to fetch location with id: ${locationId}, ${response.statusText}`);
                const data = await response.json();
                setLocation(data);
            } catch (e) {
                console.error("Error fetching location:", e);
            }
        }
        fetchLocation();
    }, [locationId]);

    async function handleLocationUpdate(e) {
        e.preventDefault();

        const token = localStorage.getItem('jwtToken');
        try {
            const response = await fetch(`/api/locations`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                 },
                body: JSON.stringify(location),
            });

            if (!response.ok) throw new Error(`Failed to update location with id: ${locationId}, ${response.statusText}`);

            const data = await response.json();

            navigate(`/locations/${locationId}`)
        } catch (e) {
            console.error("Error updating location:", e);
        }
    }

    function handleOpeningHoursChange(dayOfWeek, field, value) {
        setLocation((prevLocation) => {
            let updatedOpeningHours = prevLocation.openingHours || [];
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

    return (
        location && (
            <div>
                <h1 className='font-bold text-3xl mb-5'>Update Location</h1>
                <form onSubmit={handleLocationUpdate} className='flex flex-col items-center'>
                    <div>

                        <InputField
                            label="Name"
                            type="text"
                            value={location.name || ''}
                            onChange={(e) => setLocation({ ...location, name: e.target.value })}
                        />
                        <InputField
                            label="Address"
                            type="text"
                            value={location.address || ''}
                            onChange={(e) => setLocation({ ...location, address: e.target.value })}
                        />
                        <InputField
                            label="Phone"
                            type="text"
                            value={location.phone || ''}
                            onChange={(e) => setLocation({ ...location, phone: e.target.value })}
                        />
                        <InputField
                            label="Email"
                            type="text"
                            value={location.email || ''}
                            onChange={(e) => setLocation({ ...location, email: e.target.value })}
                        />
                        <InputField
                            label="Website"
                            type="text"
                            value={location.website || ''}
                            onChange={(e) => setLocation({ ...location, website: e.target.value })}
                        />
                        <InputField
                            label="Facebook"
                            type="text"
                            value={location.facebook || ''}
                            onChange={(e) => setLocation({ ...location, facebook: e.target.value })}
                        />
                        <InputField
                            label="Instagram"
                            type="text"
                            value={location.instagram || ''}
                            onChange={(e) => setLocation({ ...location, instagram: e.target.value })}
                        />
                        <InputField
                            label='Description'
                            type='text'
                            value={location.description || ''}
                            onChange={(e) => setLocation({ ...location, description: e.target.value })}
                        />
                        {daysOfWeek.map((dayOfWeek) => {
                            const openingHour = location.openingHours.find(hour => hour.day === dayOfWeek)

                            return (
                            <div key={dayOfWeek} className="mt-4">
                                <h3 className="font-semibold">{dayOfWeek}</h3>
                                <InputField
                                    label="Opening Time"
                                    type="time"
                                    value={openingHour ? openingHour.openingTime : ''}
                                    onChange={(e) => handleOpeningHoursChange(dayOfWeek, "openingTime", e.target.value)}
                                />
                                <InputField
                                    label="Closing Time"
                                    type="time"
                                    value={openingHour ? openingHour.closingTime : ''}
                                    onChange={(e) => handleOpeningHoursChange(dayOfWeek, "closingTime", e.target.value)}
                                />
                            </div>
                        )})}
                        <button type='submit' className='mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg'>
                            Update Location
                        </button>
                    </div>
                </form>
            </div>
        )
    )
}

export default UpdateLocationForm;