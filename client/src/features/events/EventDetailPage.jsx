/* eslint-disable no-unused-vars */
import { useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useFetchEventData } from './hooks/useFetchEventData.jsx';
import ActionButtons from '../main/components/molecules/ActionButtons.jsx';
import EventInfo from './EventInfo.jsx';

import TagList from '../main/components/molecules/TagList.jsx';
import UserHandling from '../main/components/molecules/UserHandling.jsx';

function EventDetailPage() {
  const { eventId } = useParams();
  const [userChange, setUserChange] = useState(false);

  const { event, error, loading, user, owner } = useFetchEventData(eventId);

  return (
    loading? <loading/>: (
      <div className='flex flex-col items-center justify-center'>
        <div className='p-6 my-10 border rounded-lg shadow-md bg-light-secondaryBg dark:bg-dark-secondaryBg border-light-border dark:border-dark-border w-full max-w-4xl'>
          <EventInfo event={event} />
          {event.users.length > 0 && <UserHandling setUserChange={setUserChange} event={event} />}

          {event?.tags?.length > 0 && <TagList tags={event?.tags} />}
          {owner === user && <ActionButtons id={eventId} partName={'event'} />}
        </div>
      </div>
    )
  );
}

export default EventDetailPage;
