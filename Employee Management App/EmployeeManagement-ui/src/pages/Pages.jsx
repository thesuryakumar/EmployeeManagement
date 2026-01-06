import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import AdminEmployees from "../components/AdminEmployees";
import Reviews from "../components/Reviews";
import Feedback from "../components/Feedback";
import EmployeeDashboard from "../components/EmployeeDashboard";

function Pages() {
  return (
    <div style={{ padding: 20, fontFamily: "cursive" }}>
      <h2>Employee Management App</h2>

      <nav style={{ marginBottom: 20 }}>
        <Link to="/employees" style={{ marginRight: 10 }}>
          Admin - Employees
        </Link>
        <Link to="/feedback" style={{ marginRight: 10 }}>
          Feedback
        </Link>
        <Link to="/reviews" style={{ marginRight: 10 }}>
          Reviews
        </Link>
      </nav>

      <Routes>
        <Route path="/employees" element={<AdminEmployees />} />
        <Route path="/reviews" element={<Reviews />} />
        <Route path="/feedback" element={<Feedback />} />
        <Route path="/employee/:id" element={<EmployeeDashboard />} />
        <Route path="*" element={<div>Select a page from above.</div>} />
      </Routes>
    </div>
  );
}

export default Pages;
