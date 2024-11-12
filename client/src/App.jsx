import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css'
import Home from "./features/main/Home.jsx";
import Welcome from "./features/welcome/Welcome.jsx";

import Layout from "./features/main/components/organisms/Layout.jsx";
import EventDetailPage from './features/events/EventDetailPage.jsx';
import NewEventForm from './features/events/NewEventForm.jsx';

import ModifyEventForm from './features/events/ModifyEventForm.jsx';

import NewLocationForm from "./features/locations/NewLocationForm.jsx";
import LocationDetailPage from "./features/locations/LocationDetailPage.jsx";
import { useEffect, useState } from 'react';



function App() {
   const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      children: [
        {
          path: "/",
          element: <Home />,
        },
        {
          path: "/welcome",
          element: <Welcome />,
        },
        {
          path:'/events/:eventId',
          element:<EventDetailPage/>
        },
        {
          path:'/events/new',
          element:<NewEventForm/>,
        },
        {

          path:'/events/:eventId/modify',
          element:<ModifyEventForm/>},

         { path:'/locations/new',
          element:<NewLocationForm/>,

        },
        
        {path: 'locations/:locationId',
          element:<LocationDetailPage/>
        }
      ],
    }
  ]);

  return (
    <RouterProvider router={router} />
  );
}

export default App
