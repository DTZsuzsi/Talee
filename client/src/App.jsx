import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css'
import Home from "./features/main/Home.jsx";
import Welcome from "./features/welcome/Welcome.jsx";
import Layout from "./features/main/components/organisms/Layout.jsx";

import EventRoutes from "./features/events/EventRoutes.jsx";
import LocationsRoutes from "./features/locations/LocationRoutes.jsx";
import UserRoutes from './features/users/UserRoutes.jsx';


function App() {
   const router = createBrowserRouter([
    {
      element: <Layout />,
      children: [
        { path: "/", element: <Home /> },
        { path: "/welcome", element: <Welcome /> },
        
        { path: "/events", children: EventRoutes },
        { path: "/locations", children: LocationsRoutes },
        { path: "/users", children: UserRoutes}
      ],
    }
  ]);

  

  return (
    <RouterProvider router={router} />
  );
}

export default App
