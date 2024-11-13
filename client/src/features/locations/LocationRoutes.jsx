import NewLocationForm from "./NewLocationForm";
import LocationDetailPage from "./LocationDetailPage";

const LocationRoutes = [
    { path: "new", element: <NewLocationForm /> },
    { path: ":locationId", element: <LocationDetailPage /> },
  ];
  
  export default LocationRoutes;