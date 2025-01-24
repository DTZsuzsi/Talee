import { Link } from "react-router-dom";


function EventInfo({ event }) {
  return (
    <div className="flex flex-col lg:flex-row gap-6">
      <img
        src="https://images.unsplash.com/photo-1513151233558-d860c5398176?q=80&w=3270&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        alt="Event"
        className="rounded-md w-full lg:w-1/3 object-cover"
      />
      <div className="flex flex-col gap-4 w-full">
        <div>
          <p className="text-2xl font-bold text-center">{event.name}</p>
        </div>
        <div>
          <p className="text-lg font-semibold">Date:</p>
          <p className="text-mutedText dark:text-dark-mutedText">
            {event.date}
          </p>
        </div>
        <div>
          <p className="text-lg font-semibold">Description:</p>
          <p className="text-mutedText dark:text-dark-mutedText">
            {event.description}
          </p>
        </div>
        <div>
          <p className="text-lg font-semibold">Owner:</p>
          <p className="text-mutedText dark:text-dark-mutedText">
            {event.owner.username}
          </p>
        </div>

        <div>
          <p className="text-lg font-semibold">Events Location:</p>
          <Link
            to={`/locations/${event.location.locationId}`}
            className="block p-4 border rounded-lg shadow-md bg-light-bg dark:bg-dark-bg"
          >
            <h3 className="text-xl font-bold">{event.location.name}</h3>
            <p className="text-mutedText dark:text-dark-mutedText">
              {event.location.description}
            </p>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default EventInfo;
