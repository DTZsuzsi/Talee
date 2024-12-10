import { Link } from "react-router-dom";

function EventList({ events }) {
  return (
    <div>
      <h2 className="text-2xl font-semibold">Events at this location:</h2>
      {events.map((event) => (
        <div key={event.id} className="mt-4">
        <Link
          to={`/events/${event.id}`}
          className="block p-4 border rounded-lg shadow-md bg-light-bg dark:bg-dark-bg"
        >
          <h3 className="text-xl font-bold">{event.name}</h3>
          <p className="text-mutedText dark:text-dark-mutedText">
            {event.description}
          </p>
        </Link>
      </div>
      ))}
    </div>
  );
}

export default EventList;