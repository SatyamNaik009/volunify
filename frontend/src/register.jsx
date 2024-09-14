import React, { useState } from "react";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import { useNavigate } from "react-router-dom";
import ApiService from "./service/ApiService";
export const Register = (props) => {
  const [email, setEmail] = useState("");
  const [pass, setPass] = useState("");
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const registration = {
      email: email,
      password: pass,
      name: name,
      phoneNumber: phone,
    };

    try {
      const response = await ApiService.registerUser(registration);
      console.log("Registration Successful:", response);
      setTimeout(() => {
        navigate("/", { replace: true });
      }, 2000);
    } catch (error) {
      setError("Enter valid credentials or the Email already exists");
      setTimeout(() => setError(""), 5000);
      console.error("Error during registration:", error);
    }
  };
  return (
    <div className="auth-form-container">
      <h3>
        {" "}
        <button style={{ fontWeight: "700", textAlign: "center" }}>
          REGISTER{" "}
        </button>
      </h3>
      <form className="register-form" onSubmit={handleSubmit}>
        <label htmlFor="name" style={{ fontWeight: "600" }}>
          YOUR FULL NAME
        </label>
        <input
          value={name}
          name="name"
          onChange={(e) => setName(e.target.value)}
          id="name"
          placeholder="Enter Full Name Here"
        />
        <label htmlFor="phone" style={{ fontWeight: "600" }}>
          YOUR PHONE NUMBER
        </label>
        <input
          value={phone}
          type="tel"
          id="phone"
          name="phone"
          onChange={(e) => setPhone(e.target.value)}
          placeholder="Enter Phone Number Here"
        />

        <label htmlFor="email" style={{ fontWeight: "600" }}>
          EMAIL
        </label>
        <input
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          type="email"
          placeholder="Enter Email Address"
          id="email"
          name="email"
        />
        <label htmlFor="password" style={{ fontWeight: "600" }}>
          PASSWORD
        </label>
        <input
          value={pass}
          onChange={(e) => setPass(e.target.value)}
          type="password"
          placeholder="Enter Password"
          id="password"
          name="password"
        />
        <button
          type="submit"
          style={{
            fontWeight: "700",
            backgroundColor: "darkblue",
            color: "white",
          }}
        >
          Register <ArrowForwardIcon />
        </button>
      </form>
      <button
        className="link-btn"
        onClick={() => props.onFormSwitch("login")}
        style={{ color: "black", fontWeight: "700" }}
      >
        Already have an account? Login here.
      </button>
    </div>
  );
};
