import { Route, Routes } from "react-router-dom";
import Login from "./Login.jsx";
import Register from "./Register.jsx";

function UserRoutes() {
    return (
        <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
        </Routes>
    );
}

export default UserRoutes;