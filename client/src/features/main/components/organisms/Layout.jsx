import { Outlet } from "react-router-dom";
import Header from "./Header";
import Divider from "../atoms/Divider";
import ReportButton from "../../../bugreport/ReportButton";

const Layout = () => {
    return (
			<div className='w-screen transition-colors duration-300 bg-background text-text dark:bg-darkBackground dark:text-darkText font-sans py-8 min-h-screen'>
				<Header />
				<div className='w-8/12 mx-auto'>
					<Divider />
					<Outlet />
					<ReportButton />
				</div>
			</div>
		);
};

export default Layout;