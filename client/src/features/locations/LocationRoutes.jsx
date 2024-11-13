import { Route, Routes } from "react-router-dom";
import NewLocationForm from "./NewLocationForm";
import LocationDetailPage from "./LocationDetailPage";

function LocationRoutes() {
  return (
    <Routes>
      <Route path="new" element={<NewLocationForm />} />
      <Route path=":locationId" element={<LocationDetailPage />} />
    </Routes>
  );
}

export default LocationRoutes;
