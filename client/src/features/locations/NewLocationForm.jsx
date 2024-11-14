import { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputField from "../main/components/atoms/InputField.jsx";
//import SelectField from '../main/components/atoms/SelectField';

function NewLocationForm() {
    const [newLocation, setNewLocation] = useState({
        name: '',
        address: '',
        phone: '',
        email: '',
        website: '',
        facebook: '',
        instagram: '',
        description: '',
        openingHours: []
    });
    //const [openingHour, setOpeningHour] = useState({
    //    dayOfWeek: '', 
    //    openingTime: '', 
    //    closingTime: ''
    //})

    const navigate = useNavigate();

    const daysOfWeek = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

    async function handleNewLocation(e) {
        e.preventDefault();
        const response = await fetch('/api/locations', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newLocation)
        });
        if (!response.ok) {
            console.error('Error: ', response.status, await response.text());
            return;
        }

        const createdLocationId = await response.json();
        //console.log('Created location: ', createdLocationId);
        navigate(`/locations/${createdLocationId}`);
    }

    //function addOpeningHour() {
    //    setNewLocation({
    //        ...newLocation,
    //        openingHours: [...newLocation.openingHours, { dayOfWeek: '', openingTime: '', closingTime: '' }]
    //    })
    //}


    function handleOpeningHoursChange(day, field, value) {
        setNewLocation((prevLocation) => {
            const updatedOpeningHours = prevLocation.openingHours.map(hour =>
                hour.dayOfWeek === day ? { ...hour, [field]: value } : hour
            );

            if (!updatedOpeningHours.some(hour => hour.dayOfWeek === day)) {
                updatedOpeningHours.push({ dayOfWeek: day, [field]: value });
            }

            return { ...prevLocation, openingHours: updatedOpeningHours };

        })
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
                        onChange={(e) => setNewLocation({ ...newLocation, name: e.target.value })}
                    />
                    <InputField
                        label="Address"
                        type="text"
                        value={newLocation.address}
                        onChange={(e) => setNewLocation({ ...newLocation, address: e.target.value })}
                    />
                    <InputField
                        label="Phone"
                        type="text"
                        value={newLocation.phone}
                        onChange={(e) => setNewLocation({ ...newLocation, phone: e.target.value })}
                    />
                    <InputField
                        label="Email"
                        type="text"
                        value={newLocation.email}
                        onChange={(e) => setNewLocation({ ...newLocation, email: e.target.value })}
                    />
                    <InputField
                        label="Website"
                        type="text"
                        value={newLocation.website}
                        onChange={(e) => setNewLocation({ ...newLocation, website: e.target.value })}
                    />
                    <InputField
                        label="Facebook"
                        type="text"
                        value={newLocation.facebook}
                        onChange={(e) => setNewLocation({ ...newLocation, facebook: e.target.value })}
                    />
                    <InputField
                        label="Instagram"
                        type="text"
                        value={newLocation.instagram}
                        onChange={(e) => setNewLocation({ ...newLocation, instagram: e.target.value })}
                    />
                    <InputField
                        label="Description"
                        type="text"
                        value={newLocation.description}
                        onChange={(e) => setNewLocation({ ...newLocation, description: e.target.value })}
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
                    
                    <button type="submit" className="mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg">
                        Create New Location
                    </button>
                </div>
            </form>
        </div>
    );
}

export default NewLocationForm;