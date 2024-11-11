import { Outlet } from "react-router-dom";
import Header from "./Header";
import Divider from "../atoms/Divider";
const Layout = () => {
    return (
        <div >
            <Header />
            <Divider />
            <Outlet />
        </div>
    )
};

export default Layout;