import { Outlet } from 'react-router-dom';
import ReportButton from '../../../bugreport/ReportButton';
import Navbar from "./Navbar.jsx";
import Divider from "../atoms/Divider.jsx";

const Layout = () => {
	return (
		<div>
			{/*<Header />*/}
			<Navbar />
			<div className='w-8/12 mx-auto'>
				<Divider />
				<Outlet />
			</div>
			<ReportButton />
		</div>
	);
};

export default Layout;
