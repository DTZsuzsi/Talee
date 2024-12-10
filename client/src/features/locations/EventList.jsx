import { Link } from "react-router-dom";

function EventList({ events }) {
  return (
    <div>
      <h2 className="text-2xl font-semibold">Events at this location:</h2>
      {events.map((event) => (
        <Link key={event.id} to={`/events/${event.id}`} className="block p-4 border rounded-lg shadow-md">
          <h3 className="text-xl font-bold">{event.name}</h3>
          <p>{event.description}</p>
        </Link>
      ))}
    </div>
  );
}

export default EventList;