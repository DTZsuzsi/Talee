import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import Welcome from "./features/welcome/Welcome.jsx";
import AllTagsPage from "./features/tag/AllTagsPage.jsx";
import AllTagCategoriesPage from "./features/tag/AllTagCategoriesPage.jsx";
import EventRoutes from "./features/events/EventRoutes.jsx";
import LocationsRoutes from "./features/locations/LocationRoutes.jsx";
import UserRoutes from "./features/users/UserRoutes.jsx";
import Home from "./features/main/components/organisms/Home.jsx";
import Layout from "./features/main/components/organisms/Layout.jsx";
import PageNotFound from "./features/main/components/molecules/PageNotFound.jsx";
import Login from "./features/auth/Login.jsx";
import Register from "./features/auth/Register.jsx";
import { ThemeProvider } from "./features/main/ThemeContext.jsx";
import { AuthProvider } from "./features/auth/AuthContext.jsx";

function App() {
  return (
    <ThemeProvider>
      <AuthProvider>
        <BrowserRouter
          future={{
            v7_startTransition: true,
            v7_relativeSplatPath: true,
          }}
        >
          <Routes>
            <Route path="/" element={<Layout />}>
              <Route index element={<Home />} />
              <Route path="welcome" element={<Welcome />} />
              <Route path="locations/*" element={<LocationsRoutes />} />
              <Route path="events/*" element={<EventRoutes />} />
              <Route path="users/*" element={<UserRoutes />} />
              <Route path="tags" element={<AllTagsPage />} />
              <Route path="tagcategories" element={<AllTagCategoriesPage />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="*" element={<PageNotFound />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </ThemeProvider>
  );
}

export default App;
