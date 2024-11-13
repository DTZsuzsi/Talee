import NewEventForm from "./NewEventForm";
import EventDetailPage from "./EventDetailPage";
import ModifyEventForm from "./ModifyEventForm";

const EventsRoutes = [
    { path: "new", element: <NewEventForm /> },
    { path: ":eventId", element: <EventDetailPage /> },
    { path: ":eventId/modify", element: <ModifyEventForm /> },
  ];
  
  export default EventsRoutes;
