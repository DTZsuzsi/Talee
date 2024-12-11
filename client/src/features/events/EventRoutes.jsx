import NewEventForm from "./NewEventForm";
import EventDetailPage from "./EventDetailPage";
import ModifyEventForm from "./ModifyEventForm";
import { Route, Routes } from "react-router-dom";

function EventsRoutes() {
  return (
    <Routes>
      <Route path="new/:locationId" element={<NewEventForm />} />
      <Route path=":eventId" element={<EventDetailPage />} />
      <Route path=":eventId/update" element={<ModifyEventForm />} />
    </Routes>
  );
}

export default EventsRoutes;
