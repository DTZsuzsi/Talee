/* eslint-disable no-unused-vars */
import { Link, useNavigate, useParams } from 'react-router-dom';
import ServerError from '../main/components/atoms/ServerError';
import Loading from '../main/components/atoms/Loading';
import TaleeButton from '../main/components/atoms/TaleeButton.jsx';
import MapDisplay from '../maps/MapDisplay.jsx';
import { useFetchLocationData } from './hooks/useFetchLocationData.jsx';
import LocationInfo from './LocationInfo.jsx';
import EventList from './EventList.jsx';
import TagList from '../main/components/molecules/TagList.jsx';
import ActionButtons from '../main/components/molecules/ActionButtons.jsx';

function LocationDetailPage() {
  const { locationId } = useParams();
  const navigate = useNavigate();
  const { location, events, error, loading, owner, user } = useFetchLocationData(locationId);

  return (
    <div className="flex flex-col items-center justify-center">
      <div className="p-6 my-10 border rounded-lg shadow-md bg-light-secondaryBg dark:bg-dark-secondaryBg border-light-border dark:border-dark-border w-full max-w-4xl">
        <LocationInfo location={location} />

        {location?.latitude && <MapDisplay lat={location?.latitude} lng={location?.longitude} />}
        {events.length > 0 && (
          <div>
            <EventList events={events} />
          </div>
        )}

        {location?.tags?.length > 0 && (
          <div className="mt-5">
            <TagList tags={location.tags} />
          </div>
        )}
        {owner === user && (
          <div className="flex justify-end gap-4 mt-6">
            <ActionButtons id={locationId} partName={"location"} />

            <Link to={`/events/new/${locationId}`}>
              <TaleeButton> Create event</TaleeButton>
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}

export default LocationDetailPage;
