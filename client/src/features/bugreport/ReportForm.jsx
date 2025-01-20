/** @format */

//loadingra és errorra csinálhatunk usePost custom hookot, vagy itt írjuk át, mindenesetre itt loading nem jó

import { useState } from "react";
import Loading from "../main/components/atoms/Loading";
import axiosInstance from "../../axiosInstance.jsx";

const ReportForm = ({ onClose, type }) => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);
  const [report, setReport] = useState({
    title: "",
    description: "",
    reportType: type || "BUG",
  });

  const handleChange = (e) => {
    setReport({ ...report, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await axiosInstance.post("/reports", report);
      setSuccess(true);
      setError(null);
    } catch (err) {
      console.error("Error submitting report:", err);
      setError("Failed to submit report");
    } finally {
      setLoading(false);
      setTimeout(onClose, 2000);
    }
  };

  if (error) {
    return (
      <div className="flex justify-center items-center">
        <p className="text-red-500">{error}</p>
      </div>
    );
  }

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Title"
        className="border p-2 w-full mb-4"
        name="title"
        value={report.title}
        onChange={handleChange}
        required
      />
      <textarea
        className="border p-2 w-full mb-4"
        rows="4"
        placeholder="Please describe the issue..."
        name="description"
        value={report.description}
        onChange={handleChange}
        required
      ></textarea>
      {success ? (
        <p className="text-green-500">Report submitted successfully!</p>
      ) : (
        <div className="flex justify-between mx-2">
          {loading ? (
            <Loading />
          ) : (
            <button
              type="submit"
              className="px-4 py-2 bg-[#3B82F6] hover:bg-[#2563EB] text-white rounded-md"
            >
              Submit
            </button>
          )}
          <button
            type="button"
            onClick={onClose}
            className="px-4 py-2 bg-red-400 hover:bg-red-500 text-white rounded-md"
          >
            Close
          </button>
        </div>
      )}
    </form>
  );
};

export default ReportForm;
