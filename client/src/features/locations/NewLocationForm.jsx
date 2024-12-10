import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TaleeButton from '../main/components/atoms/TaleeButton.jsx';
import Loading from '../main/components/atoms/Loading.jsx';
import ServerError from '../main/components/atoms/ServerError.jsx';
import GoogleMapComponent from '../maps/GoogleMapComponent.jsx';
import TagOptions from '../tag/components/TagOptions.jsx';
import TagCard from '../tag/components/TagCard.jsx';
import { useFetchTags } from '../main/components/hooks/useFetchTags.jsx';
import LocationForm from './LocationForm.jsx';
import { deleteAndAddTag } from '../main/tagHandling/deleteAndAddTag.jsx';

function NewLocationForm() {
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [position, setPosition] = useState({ lat: 0, lng: 0 });
  const [address, setAddress] = useState('');

  const [newLocation, setNewLocation] = useState({
    name: '',
    address: address,
    phone: '',
    email: '',
    website: '',
    facebook: '',
    instagram: '',
    description: '',
    latitude: position.lat,
    longitude: position.lng,
    openingHours: [],
    tags: [],
  });

  const navigate = useNavigate();

  const { tags } = useFetchTags();
  const {handleNewTag, handleDeleteTag}=deleteAndAddTag(newLocation,setNewLocation,tags)

  useEffect(() => {
    setNewLocation((prev) => ({
      ...prev,
      latitude: position.lat,
      longitude: position.lng,
    }));
  }, [position]);

  function handleOpeningHoursChange(day, field, value) {
    setNewLocation((prevLocation) => {
      const updatedHours = prevLocation.openingHours || [];
      const existingDayIndex = updatedHours.findIndex((hour) => hour.day === day);

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

  // function handleNewTag(e) {
  //   const selectedTagName = e.target.value;
  //   const tagToAdd = tags?.find((tag) => tag.name === selectedTagName);

  //   if (!tagToAdd) {
  //     console.warn(`Tag with name ${selectedTagName} not found.`);
  //     return;
  //   }

  //   if (newLocation.tags.some((tag) => tag.id === tagToAdd.id)) {
  //     console.warn(`Tag with ID ${tagToAdd.id} is already added.`);
  //     return;
  //   }

  //   setNewLocation((prevLocation) => ({
  //     ...prevLocation,
  //     tags: [...prevLocation.tags, tagToAdd],
  //   }));
  // }

  // function handleDeleteTag(tag) {
  //   setNewLocation((prevLocation) => ({
  //     ...prevLocation,
  //     tags: prevLocation.tags.filter((t) => t.id !== tag.id),
  //   }));
  // }

  async function handleNewLocation(e) {
    e.preventDefault();
    setLoading(true);
    const token = localStorage.getItem('jwtToken');

    try {
      const response = await fetch('/api/locations', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(newLocation),
      });

      if (!response.ok) throw new Error('Failed to create location');

      const createdLocationId = await response.json();
      navigate(`/locations/${createdLocationId}`);
    } catch (error) {
      console.error('Error creating location:', error);
      setError('Failed to create location');
    } finally {
      setLoading(false);
    }
  }

  if (error) {
    return <ServerError error={error} />;
  }

  return (
    <div className='flex justify-center py-10'>
      <form
        onSubmit={handleNewLocation}
        className='bg-light-secondaryBg dark:bg-dark-secondaryBg text-light-text dark:text-dark-text shadow-md rounded-lg p-8 w-full max-w-4xl'
      >
        <h1 className='font-bold text-3xl text-center mb-8'>Create New Location</h1>

        {/* Basic Information */}
        <LocationForm location={newLocation} setLocation={setNewLocation} onHoursChange={handleOpeningHoursChange} />

        {/* Tags */}
        <div className='mt-6'>
          <TagOptions onChange={handleNewTag} />
          <ul className='grid grid-cols-2 md:grid-cols-4 gap-4'>
            {newLocation.tags.map((tag) => (
              <li key={tag.id}>
                <TagCard tag={tag} onClick={() => handleDeleteTag(tag)} color={tag.color} className='w-full' />
              </li>
            ))}
          </ul>
        </div>

        {/* Map */}
        <div className='mt-6'>
          <GoogleMapComponent position={position} setPosition={setPosition} address={address} setAddress={setAddress} />
        </div>

        {/* Submit Button */}
        <div className='w-full flex justify-center mt-6'>
          {loading ? (
            <Loading />
          ) : (
            <TaleeButton type='submit' className='mt-5'>
              Create Location
            </TaleeButton>
          )}
        </div>
      </form>
    </div>
  );
}

export default NewLocationForm;
