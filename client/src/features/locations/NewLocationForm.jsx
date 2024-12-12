import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TaleeButton from '../main/components/atoms/TaleeButton.jsx';
import Loading from '../main/components/atoms/Loading.jsx';
import ServerError from '../main/components/atoms/ServerError.jsx';
import GoogleMapComponent from '../maps/GoogleMapComponent.jsx';
import LocationForm from './LocationForm.jsx';
import useOpeningHours from './hooks/useOpeningHours.jsx';
import TagListModify from '../main/components/molecules/TagListModify.jsx';
import useFetchTags from "../main/components/hooks/useFetchTags.jsx";
import axiosInstance from "../../axiosInstance.jsx";

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

  const { handleOpeningHoursChange } = useOpeningHours(setNewLocation);

  useEffect(() => {
    setNewLocation((prev) => ({
      ...prev,
      latitude: position.lat,
      longitude: position.lng,
    }));
  }, [position]);

  async function handleNewLocation(e) {
    e.preventDefault();
    setLoading(true);
    console.log(newLocation);

    try {
      const response = await axiosInstance.post('/locations', newLocation);

      const createdLocationId = response.data;
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

        <LocationForm location={newLocation} setLocation={setNewLocation} onHoursChange={handleOpeningHoursChange} />

        <TagListModify partName={newLocation} setter={setNewLocation} tags={tags} />
        <div className='mt-6'>
          <GoogleMapComponent position={position} setPosition={setPosition} address={address} setAddress={setAddress} />
        </div>

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
