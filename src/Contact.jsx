import React, { useState } from "react";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import ApiService from "./service/ApiService";
import { useNavigate } from "react-router-dom"; // Import the function from your service file

const Contact = () => {
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    category: "",
    location: "",
    city: "",
    skillsRequired: "",
    startDate: "",
    endDate: "",
  });

  const [file, setFile] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userId = localStorage.getItem("userId");

    if (!userId) {
      console.error("User ID not found in local storage");
      return;
    }

    try {
      const response = await ApiService.addOpportunity(formData, file, userId); // Call the function here
      console.log("Opportunity added:", response);
      navigate("/Enroll", { replace: true });
    } catch (error) {
      console.error("Error adding opportunity:", error);
    }
  };

  return (
    <div
      style={{
        backgroundImage:
          "url('https://img.freepik.com/free-vector/raised-colorful-volunteers-hands-concept-background-with-love-heart-vector_1017-48261.jpg?uid=R121066045&ga=GA1.1.1977216938.1680318996&semt=ais_hybrid')",
      }}
    >
      <h2
        style={{
          textAlign: "center",
          fontWeight: "bold",
          fontFamily: "cursive",
        }}
      >
        Post volunteering opportunity here
      </h2>
      <form
        onSubmit={handleSubmit}
        style={{ textAlign: "center", fontFamily: "cursive" }}
      >
        <input
          type="text"
          name="title"
          placeholder="Title of your social work"
          style={{ width: "300px", textAlign: "center" }}
          value={formData.title}
          onChange={handleChange}
        />
        <br />
        <br />
        <input
          type="text"
          name="description"
          placeholder="Description"
          style={{ width: "300px", textAlign: "center" }}
          value={formData.description}
          onChange={handleChange}
        />
        <br />
        <br />

        <label
          htmlFor="category"
          style={{ fontWeight: "bolder", color: "gray" }}
        >
          Choose a category:
        </label>
        <select
          name="category"
          value={formData.category}
          onChange={handleChange}
          style={{ width: "300px", textAlign: "center" }}
        >
          <option value="Education">Education</option>
          <option value="Healthcare">Healthcare</option>
          <option value="Food">Donate Food</option>
          <option value="Clean India">Clean India</option>
        </select>
        <br />
        <br />

        <input
          type="text"
          name="location"
          placeholder="Enter Location Here"
          style={{ width: "300px", textAlign: "center" }}
          value={formData.location}
          onChange={handleChange}
        />
        <br />
        <br />

        <label htmlFor="city" style={{ fontWeight: "bolder", color: "gray" }}>
          Enter the City:
        </label>
        <input
          type="text"
          name="city"
          placeholder="Enter City"
          style={{ width: "300px", textAlign: "center" }}
          value={formData.city}
          onChange={handleChange}
        />
        <br />
        <br />

        <input
          type="text"
          name="skillsRequired"
          placeholder="What Skills you want?"
          style={{ width: "300px", textAlign: "center" }}
          value={formData.skillsRequired}
          onChange={handleChange}
        />
        <br />
        <br />

        <label htmlFor="start">Start-date:</label>
        <input
          type="date"
          id="start"
          name="startDate"
          value={formData.startDate}
          onChange={handleChange}
        />
        <br />
        <br />

        <label htmlFor="end">End-date:</label>
        <input
          type="date"
          id="end"
          name="endDate"
          value={formData.endDate}
          onChange={handleChange}
        />
        <br />
        <br />

        <label htmlFor="file">Upload an image:</label>
        <input type="file" id="file" onChange={handleFileChange} />
        <br />
        <br />

        <button
          type="submit"
          style={{
            background: "black",
            color: "white",
            border: "1px solid white",
            height: "60px",
            borderRadius: "10%",
          }}
        >
          Submit <ArrowForwardIcon />
        </button>
      </form>
    </div>
  );
};

export default Contact;
