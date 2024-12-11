import { useState } from "react";
import axios from "axios";
import { useAuth } from "../AuthContext.jsx";

export const useLogin = () => {
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const login = async (username, password) => {
    setError("");
    setSuccess("");
    try {
      const response = await axios.post(`/api/auth/login`, {
        username,
        password,
      });

      const { jwtToken, userName, roles, message } = response.data;
      const userData = {
        jwtToken,
        userName,
        roles,
      };

      setSuccess(message);

      return { userData };
    } catch (err) {
      setError(
        err.response?.data?.message ||
          "Authentication failed. Please try again.",
      );
    }
  };

  return { login, error, success };
};
