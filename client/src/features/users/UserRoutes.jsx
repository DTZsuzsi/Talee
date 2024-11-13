import { Route, Routes } from "react-router-dom";
import UserDetailPage from "./UserDetailPage";

function UserRoutes() {
        return (
                <Routes>
                        <Route index path=":userId" element={<UserDetailPage />} />
                </Routes>
        );
}
    
export default UserRoutes;