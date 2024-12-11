import React, { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext(null);

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    getUser();
  }, []);

  const getUser = () => {
    if (user) {
      return user;
    }

    const username = JSON.parse(localStorage.getItem("username"));
    const jwtToken = JSON.parse(localStorage.getItem("jwtToken"));
    const roles = JSON.parse(localStorage.getItem("roles"));

    const storageUser = { username, jwtToken, roles };
    if (storageUser) {
      setUser(storageUser);
    }

    return storageUser;
  };

  const saveUser = (userData) => {
    setUser(userData);
    localStorage.setItem("username", JSON.stringify(userData.userName));
    localStorage.setItem("jwtToken", JSON.stringify(userData.jwtToken));
    localStorage.setItem("roles", JSON.stringify(userData.roles));
  };

  const isLoggedIn = () => {
    const user = localStorage.getItem("username");
    return !!user;
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem("username");
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("roles");
  };

  return (
    <AuthContext.Provider
      value={{ user, saveUser, logout, isLoggedIn, getUser }}
    >
      {children}
    </AuthContext.Provider>
  );
};
