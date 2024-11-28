import { Route, Routes } from "react-router-dom";
import NewLocationForm from "./NewLocationForm";
import UpdateLocationForm from "./UpdateLocationForm.jsx";
import LocationDetailPage from "./LocationDetailPage.jsx";

function LocationRoutes() {
  return (
    <Routes>
      <Route path="new" element={<NewLocationForm />} />
      <Route path=":locationId" element={<LocationDetailPage />} />
      <Route path=":locationId/update" element={<UpdateLocationForm />} />
    </Routes>
  );
}

export default LocationRoutes;
