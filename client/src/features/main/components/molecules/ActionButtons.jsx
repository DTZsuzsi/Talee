/* eslint-disable no-unused-vars */
import { Link } from "react-router-dom";
import { HiMiniPencilSquare } from "react-icons/hi2";
import { MdDeleteForever } from "react-icons/md";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

import BiggerOnHover from "../atoms/BiggerOnHover";
import Loading from "../atoms/Loading";
import axiosInstance from "../../../../axiosInstance.jsx";

function ActionButtons({ id, partName }) {
  const [loading, setLoading] = useState(false);
  const [deleteError, setDeleteError] = useState(null);
  const navigate = useNavigate();

  async function handleDelete() {
    setLoading(true);
    setDeleteError(null);
    try {

      //s ne legyen

      const response = await axiosInstance.delete(`/${partName}s/${id}`);
      if (response.status === 200) {
        console.log("deleted");
        navigate("/");
      } else {
        setDeleteError("Failed to delete" + partName);
      }
    } catch (err) {
      setDeleteError(
        err.response?.data?.message || "An unexpected error occurred.",
      );
    } finally {
      setLoading(false);
    }
  }

  if (!partName || loading) {
    return <Loading />;
  }
  return (
    <div className="flex justify-start gap-4">
      <BiggerOnHover>
        <Link
          to={`/${partName}s/${id}/update`}
          className="flex items-center justify-center w-12 h-12 rounded-full bg-button text-white hover:bg-buttonHover"
        >
          <HiMiniPencilSquare className="w-6 h-6" />
        </Link>
      </BiggerOnHover>

      <BiggerOnHover>
        <button
          onClick={handleDelete}
          className="flex items-center justify-center w-12 h-12 rounded-full bg-button text-white hover:bg-red-600"
        >
          <MdDeleteForever className="w-6 h-6" />
        </button>
      </BiggerOnHover>
    </div>
  );
}

export default ActionButtons;
