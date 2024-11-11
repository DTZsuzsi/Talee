import {useState} from "react";
import {useNavigate} from "react-router-dom";
import InputField from "../main/components/atoms/InputField.jsx";

function NewLocationForm() {
    const [newLocation, setNewLocation] = useState({
        name: '',
        address: '',
        phone: '',
        email: '',
        website: '',
        facebook: '',
        instagram: '',
        description: ''
    });

    const navigate = useNavigate();

    async function handleNewLocation(e) {
        e.preventDefault();
        const response = await fetch('http://localhost:8080/api/locations', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newLocation)
        });
        if (!response.ok) {
            console.error('Error: ', response.status, await response.text());
            return;
        }

        const createdLocationId = await response.json();
        console.log('Created location: ', createdLocationId);
        //TODO implement location details page
        navigate(`/locations/${createdLocationId}`);
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
                    <InputField
                        label="Address"
                        type="text"
                        value={newLocation.address}
                        onChange={(e) => setNewLocation({...newLocation, address: e.target.value})}
                    />
                    <InputField
                        label="Phone"
                        type="text"
                        value={newLocation.phone}
                        onChange={(e) => setNewLocation({...newLocation, phone: e.target.value})}
                    />
                    <InputField
                        label="Email"
                        type="text"
                        value={newLocation.location_id}
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
                    <button type="submit" className="mt-4 bg-blue-500 text-white py-2 px-4 rounded-lg">
                        Create New Location
                    </button>
                </div>
            </form>
        </div>
    );
}

export default NewLocationForm;