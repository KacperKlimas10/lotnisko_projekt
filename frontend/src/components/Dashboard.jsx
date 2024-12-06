import React from "react";
import { useNavigate } from "react-router-dom";

function Dashboard() {
    const navigate = useNavigate();

    const handleNavigation = (role) => {
        navigate(`/${role}`);
    };

    // nav handlers
    return (
        <div>
            <h2>Dashboard</h2>
            <div className="icon-grid">
                <button onClick={() => handleNavigation("security")}>Security Guard Pane</button>
                <button onClick={() => handleNavigation("admin")}>Administrator Pane</button>
                <button onClick={() => handleNavigation("pilot")}>Pilot Pane</button>
                <button onClick={() => handleNavigation("staff")}>Airport Staff Pane</button>
            </div>
            <div className="top-right">
                <button onClick={() => navigate("/account")}>Manage Account</button>
            </div>
        </div>
    );
}

export default Dashboard;
