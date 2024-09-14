import React, { useState, useEffect } from "react";
import BasicExample from "./BasicExample";
import ApiService from "./service/ApiService";
import "./enroll.css";

const Enroll = () => {
  const [cities, setCities] = useState([]);
  const [opportunities, setOpportunities] = useState([]);
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

  // Fetch opportunities
  useEffect(() => {
    const fetchOpportunities = async () => {
      try {
        const data = await ApiService.getAllOpportunities();
        setOpportunities(data.opportunityDetailsList);
      } catch (error) {
        setError("Failed to fetch opportunities.");
      }
    };

    fetchOpportunities();
  }, []);

  // Retrieve userId from local storage
  useEffect(() => {
    const storedUserId = localStorage.getItem("userId");
    if (storedUserId) {
      setUserId(storedUserId);
    }
  }, []);

  const handleEnroll = async (userId, opportunityId) => {
    try {
      console.log(localStorage.getItem("userId"));
      console.log(userId);
      console.log(opportunityId);
      const result = await ApiService.addEnrollment(
        Number(userId),
        Number(opportunityId)
      );
      return result; // Return the result from the API (true/false)
    } catch (error) {
      return false; // Return false in case of failure
    }
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
              onEnroll={handleEnroll}
            />
          ))
        ) : (
          <p>No opportunities available at the moment.</p>
        )}
      </div>
    </div>
  );
};

export default Enroll;
