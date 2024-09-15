import React, { useState, useEffect } from "react";
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
  enrolled: initialEnrolled,
  onEnroll,
}) => {
  const [enrolled, setEnrolled] = useState(initialEnrolled);
  const [enrolling, setEnrolling] = useState(false);

  useEffect(() => {
    setEnrolled(initialEnrolled);
  }, [initialEnrolled]);

  const handleEnrollClick = async () => {
    if (!enrolled && !enrolling) {
      setEnrolling(true);
      try {
        const isSuccess = await onEnroll(userId, opportunityId);
        if (isSuccess) {
          setEnrolled(true);
        } else {
          alert("Enrollment failed.");
        }
      } catch (error) {
        alert("An error occurred during enrollment.");
      }
      setEnrolling(false);
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
        disabled={enrolled || enrolling}
      >
        {enrolling ? "Enrolling..." : enrolled ? "Enrolled" : "Enroll Now"}
      </button>
    </div>
  );
};

export default BasicExample;
