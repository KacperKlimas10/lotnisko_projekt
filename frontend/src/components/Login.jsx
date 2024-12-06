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
            // const response = await fetch("/auth/login", {
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
        <div className="login">
            <div classnName="left-pane"></div>
            <div className="right-pane">
                <div className="login-container-right">
                    <h2>Welcome to AMS PK</h2>
                    <div className="login-inputs">
                        <h3>Username</h3>
                        <input type="text" placeholder="Input username" onChange={(e) => setCredentials({...credentials, username: e.target.value})}/>
                    </div>
                    <div className="login-inputs">
                        <h3>Password</h3>
                        <input type="password" placeholder="Input password" onChange={(e) => setCredentials({...credentials, password: e.target.value})}/>
                    </div>
                    <button onClick={handleLogin} className="confirm-btn">Sign in</button>
                    {error && <p style={{color: "red"}}>{error}</p>}
                </div>
            </div>
        </div>
    );
}

export default Login;
