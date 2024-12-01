import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
    const [credentials, setCredentials] = useState({ username: "", password: "" });
    const [error, setError] = useState("");
    const navigate = useNavigate();


    // async function for login (commented the backend endpoint attempt for testing reasons)
    const handleLogin = async () => {
        try {
            navigate("/dashboard")
            // const response = await fetch("/api/authenticate", {
            //     method: "POST",
            //     headers: { "Content-Type": "application/json" },
            //     body: JSON.stringify(credentials),
            // });
            //
            // if (response.ok) {
            //     const data = await response.json();
            //     localStorage.setItem("authToken", data.token); // Save JWT (json web token)
            //     navigate("/dashboard");
            // } else {
            //     setError("Invalid username or password");
            // }
        } catch (err) {
            setError("An error occurred. Please try again.");
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <input
                type="text"
                placeholder="Username"
                onChange={(e) => setCredentials({ ...credentials, username: e.target.value })}
            />
            <input
                type="password"
                placeholder="Password"
                onChange={(e) => setCredentials({ ...credentials, password: e.target.value })}
            />
            <button onClick={handleLogin}>Login</button>
            {error && <p style={{ color: "red" }}>{error}</p>}
        </div>
    );
}

export default Login;
