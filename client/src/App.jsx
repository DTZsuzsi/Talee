/** @format */

import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css';
import Home from './features/main/Home.jsx';
import Welcome from './features/welcome/Welcome.jsx';
import Layout from './features/main/components/organisms/Layout.jsx';

import LocationDetailPage from './features/locations/LocationDetailPage.jsx';
import AllTagsPage from './features/tag/AllTagsPage.jsx';
import AllTagCategoriesPage from './features/tag/AllTagCategoriesPage.jsx';

import EventRoutes from './features/events/EventRoutes.jsx';
import LocationsRoutes from './features/locations/LocationRoutes.jsx';
import UserRoutes from './features/users/UserRoutes.jsx';
import TagListTest from './features/tag/TagListTest.jsx';

function App() {
	const router = createBrowserRouter([
		{
			element: <Layout />,
			children: [
				{ path: '/', element: <Home /> },
				{ path: '/welcome', element: <Welcome /> },

				{ path: '/events', children: EventRoutes },
				{ path: '/locations', children: LocationsRoutes },
				{ path: '/users', children: UserRoutes },
				{ path: 'locations/:locationId', element: <LocationDetailPage /> },
				{ path: '/tags', element: <AllTagsPage /> },
        { path: '/tagcategories', element: <AllTagCategoriesPage /> },
        {path: '/test-tags', element: <TagListTest />}
			],
		},
	]);

	return <RouterProvider router={router} />;
}

export default App;
