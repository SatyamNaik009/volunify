import React, { useState, useEffect } from "react";
import BasicExample from "./BasicExample";
import ApiService from "./service/ApiService";
import "./enroll.css";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";

const Enroll = () => {
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState("");
  const [opportunities, setOpportunities] = useState([]);
  const [userEnrollments, setUserEnrollments] = useState([]);
  const [error, setError] = useState("");
  const [userId, setUserId] = useState(null);

  // Fetch cities
  useEffect(() => {
    const fetchCities = async () => {
      try {
        const data = await ApiService.getAllCities();
        setCities(data);
      } catch (error) {
        setError("Failed to fetch cities.");
      }
    };

    fetchCities();
  }, []);

  // Fetch all opportunities
  const fetchAllOpportunities = async () => {
    try {
      const data = await ApiService.getAllOpportunities();
      setOpportunities(data.opportunityDetailsList);
    } catch (error) {
      setError("Failed to fetch all opportunities.");
    }
  };

  // Fetch user enrollments
  useEffect(() => {
    const fetchUserEnrollments = async () => {
      if (userId) {
        try {
          console.log(userId);
          const enrollmentData = await ApiService.getUserEnrollment(userId);
          setUserEnrollments(enrollmentData);
        } catch (error) {
          setError("Failed to fetch user enrollments.");
        }
      }
    };

    fetchUserEnrollments();
  }, [userId]);

  // Fetch opportunities based on the selected city
  useEffect(() => {
    const fetchOpportunitiesByCity = async () => {
      if (selectedCity) {
        try {
          const data = await ApiService.getAllOpportunitiesByCity(selectedCity);
          setOpportunities(data.opportunityDetailsList);
        } catch (error) {
          setError("Failed to fetch opportunities for the selected city.");
        }
      } else {
        // Call fetchAllOpportunities if no city is selected
        fetchAllOpportunities();
      }
    };

    fetchOpportunitiesByCity();
  }, [selectedCity]);

  // Retrieve userId from local storage
  useEffect(() => {
    const storedUserId = localStorage.getItem("userId");
    if (storedUserId) {
      setUserId(storedUserId);
    }
  }, []);

  const handleEnroll = async (userId, opportunityId) => {
    try {
      const result = await ApiService.addEnrollment(
        Number(userId),
        Number(opportunityId)
      );
      return result; // Return the result from the API (true/false)
    } catch (error) {
      return false; // Return false in case of failure
    }
  };

  const isEnrolled = (opportunityId) => {
    return userEnrollments.some(
      (enrollment) => enrollment.opportunityId === opportunityId
    );
  };

  return (
    <div>
      <br />
      <h5
        style={{
          fontFamily: "cursive",
          fontWeight: "bolder",
          color: "maroon",
          fontSize: "27px",
          textAlign: "center",
        }}
      >
        "I am only one, but I am one. I cannot do everything, but I can do
        something. And I will not let what I cannot do interfere with what I can
        do." — Edward Everett Hale
      </h5>
      <br />
      <h2
        style={{
          fontFamily: "fantasy",
          fontWeight: "bolder",
          color: "maroon",
          fontSize: "34px",
          textAlign: "center",
        }}
      >
        SHOW YOUR SUPPORT
      </h2>

      {/* Dropdown to select a city */}
      <div
        style={{
          textAlign: "center",
          marginBottom: "20px",
          fontFamily: "cursive",
          fontSize: "20px",
        }}
      >
        <select
          value={selectedCity}
          onChange={(e) => setSelectedCity(e.target.value)}
          style={{
            background: "black",
            color: "white",
            border: "1px solid black",
            borderRadius: "50%",
            marginLeft: "7px",
            padding: "10px",
          }}
        >
          <option value="">All Cities</option>
          {cities.map((city) => (
            <option key={city} value={city}>
              {city}
            </option>
          ))}
        </select>
      </div>

      <div className="opportunity-grid">
        {opportunities.length > 0 ? (
          opportunities.map((opportunity) => (
            <BasicExample
              key={opportunity.opportunityId}
              imgsrc={opportunity.photoUrl || "https://via.placeholder.com/150"}
              title={opportunity.title}
              author={opportunity.description}
              description={opportunity.description}
              startDate={opportunity.startDate}
              endDate={opportunity.endDate}
              location={opportunity.location}
              opportunityId={opportunity.opportunityId}
              userId={userId}
              enrolled={isEnrolled(opportunity.opportunityId)} // Check if the user is already enrolled
              onEnroll={handleEnroll}
            />
          ))
        ) : (
          <p>No opportunities available.</p>
        )}
      </div>
    </div>
  );
};

export default Enroll;
