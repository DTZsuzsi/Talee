/* eslint-disable no-unused-vars */

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
        e.preventDefault();
        setLoading(true);
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
    

        if (!response.ok) {
            console.error('Error: ', response.status, await response.text());
            setLoading(false);
            setError('Failed to create location');
            return;
        }

    }
  

  function handleOpeningHoursChange(day, field, value) {
    setNewLocation((prevLocation) => {
      const updatedHours = prevLocation.openingHours || [];
      const existingDayIndex = updatedHours.findIndex(
        (hour) => hour.day === day,
      );

      if (existingDayIndex !== -1) {
        updatedHours[existingDayIndex] = {
          ...updatedHours[existingDayIndex],
          [field]: value,
        };
      } else {
        updatedHours.push({ day, [field]: value });
      }

      return { ...prevLocation, openingHours: updatedHours };
    });
  }

  const [tags, setTags] = useState(null);

  useEffect(() => {
    async function fetchTags() {
      const response = await fetch("/api/tags");
      const data = await response.json();
      setTags(data);
    }

    fetchTags();
  }, []);

//   function handleNewTag(e) {
//     const selectedTagName = e.target.value;
//     const tagToAdd = findTag(selectedTagName);

//     if (!tagToAdd) {
//       console.warn(`Tag with name ${selectedTagName} not found.`);
//       return;
//     }

//     // Add the tag to the `newEvent.tags` if it's not already present
//     setNewLocation((prevLocation) => {
//       const isTagAlreadyAdded = prevLocation.tags.includes(tagToAdd);

//       if (isTagAlreadyAdded) {
//         console.warn(`Tag with ID ${tagToAdd.id} is already added.`);
//         return prevLocation; // No changes if the tag is already in the array
//       }

//       return {
//         ...prevLocation,
//         tags: [...prevLocation.tags, tagToAdd],
//       };
//     });
//   }

//   function handleDeleteTag(tag) {
//     // Remove the tag from `newEvent.tags`
//     setNewLocation((prevLocation) => ({
//       ...prevLocation,
//       tags: prevLocation.tags.filter(
//         (existingTag) => existingTag.id !== tag.id,
//       ),
//     }));
//   }

//   function findTag(tagName) {
//     if (!tags) {
//       console.warn("Tags not loaded yet.");
//       return null;
//     }
// }


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

    // Find the tag by name from the `tags` state
    // return tags.find((tag) => tag.name === tagName) || null;
   
);
}

export default NewLocationForm;
