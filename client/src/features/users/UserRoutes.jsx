import { Route, Routes } from "react-router-dom";
import UserDetailPage from "./UserDetailPage";
import Profile from "./Profile.jsx";

function UserRoutes() {
        return (
                <Routes>
                    <Route index path="profile" element={<Profile />} />
                    <Route index path=":userId" element={<UserDetailPage />} />
                </Routes>
        );
}
    
export default UserRoutes;