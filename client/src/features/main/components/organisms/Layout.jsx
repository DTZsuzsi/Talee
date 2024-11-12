import { Outlet } from "react-router-dom";
import Header from "./Header";
import Divider from "../atoms/Divider";
const Layout = () => {
    return (
        <div className="bg-background text-text dark:bg-text dark:text-background font-sans">
            <Header />
            <Divider />
            <Outlet />
        </div>
    )
};

export default Layout;