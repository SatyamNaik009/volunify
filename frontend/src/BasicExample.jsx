import React, { useState } from "react";
import "./card.css";

const BasicExample = ({
  imgsrc,
  title,
  author,
  description,
  startDate,
  endDate,
  location,
  opportunityId,
  userId,
  onEnroll,
}) => {
  const [enrolled, setEnrolled] = useState(false);
  const [loading, setLoading] = useState(false); // Track loading state

  const handleEnrollClick = async () => {
    if (!enrolled) {
      setLoading(true); // Start loading before the API call
      try {
        const isSuccess = await onEnroll(userId, opportunityId);
        if (isSuccess) {
          setEnrolled(true); // Only update to "Enrolled" if API is successful
        } else {
          alert("Enrollment failed.");
        }
      } catch (error) {
        alert("An error occurred during enrollment.");
      }
      setLoading(false); // End loading after the API call
    }
  };

  return (
    <div className="card">
      <div className="card-img-wrapper">
        <img src={imgsrc} alt={title} className="card-img" />
        <div className="card-details">
          <p>
            <strong>Description:</strong> {description}
          </p>
          <p>
            <strong>Location:</strong> {location}
          </p>
          <p>
            <strong>Start Date:</strong> {startDate}
          </p>
          <p>
            <strong>End Date:</strong> {endDate}
          </p>
        </div>
      </div>
      <div className="card-content">
        <h3>{title}</h3>
        <p>{author}</p>
      </div>
      <button
        className="enroll-button"
        onClick={handleEnrollClick}
        disabled={enrolled || loading} // Disable button if enrolled or loading
      >
        {loading ? "Enrolling..." : enrolled ? "Enrolled" : "Enroll Now"}
      </button>
    </div>
  );
};

export default BasicExample;
