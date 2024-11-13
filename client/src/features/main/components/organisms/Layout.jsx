import { Outlet } from "react-router-dom";
import Header from "./Header";
import Divider from "../atoms/Divider";
const Layout = () => {
    return (
      <div className="w-screen transition-colors duration-300 bg-background text-text dark:bg-darkBackground dark:text-darkText font-sans py-8 min-h-screen">
        <div className="w-8/12 mx-auto">
          <Header />
          <Divider />
          <Outlet />
        </div>
      </div>
    );
  };

export default Layout;