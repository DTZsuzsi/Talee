import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import InputField from "../main/components/atoms/InputField.jsx";
import SelectField from "../main/components/atoms/SelectField.jsx";

function UpdateLocationForm() {
    const { locationId } = useParams();
    const [location, setLocation] = useState(null);

    const navigate = useNavigate();

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

        try {
            const response = await fetch(`/api/locations`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(location),
            });

            if (!response.ok) throw new Error(`Failed to update location with id: ${locationId}, ${response.statusText}`);

            const data = await response.json();

            navigate(`/locations/${locationId}`)
        } catch (e) {
            console.error("Error updating location:", e);
        }
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
                            onChange={(e) => setLocation({...location, name: e.target.value})}
                        />
                        <InputField
                            label="Address"
                            type="text"
                            value={location.address || ''}
                            onChange={(e) => setLocation({...location, address: e.target.value})}
                        />
                        <InputField
                            label="Phone"
                            type="text"
                            value={location.phone || ''}
                            onChange={(e) => setLocation({...location, phone: e.target.value})}
                        />
                        <InputField
                            label="Email"
                            type="text"
                            value={location.email || ''}
                            onChange={(e) => setLocation({...location, email: e.target.value})}
                        />
                        <InputField
                            label="Website"
                            type="text"
                            value={location.website || ''}
                            onChange={(e) => setLocation({...location, website: e.target.value})}
                        />
                        <InputField
                            label="Facebook"
                            type="text"
                            value={location.facebook || ''}
                            onChange={(e) => setLocation({...location, facebook: e.target.value})}
                        />
                        <InputField
                            label="Instagram"
                            type="text"
                            value={location.instagram || ''}
                            onChange={(e) => setLocation({...location, instagram: e.target.value})}
                        />
                        <InputField
                            label='Description'
                            type='text'
                            value={location.description || ''}
                            onChange={(e) => setLocation({...location, description: e.target.value})}
                        />
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