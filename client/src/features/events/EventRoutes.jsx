import NewEventForm from "./NewEventForm";
import EventDetailPage from "./EventDetailPage";
import ModifyEventForm from "./ModifyEventForm";
import { Route, Routes } from "react-router-dom";

function EventsRoutes() {
  return (
    <Routes>
      <Route path="new" element={<NewEventForm />} />
      <Route path=":eventId" element={<EventDetailPage />} />
      <Route path=":eventId/modify" element={<ModifyEventForm />} />
    </Routes>
  );
}

export default EventsRoutes;
