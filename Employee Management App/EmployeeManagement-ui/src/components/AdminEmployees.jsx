import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
  getEmployees,
  createEmployee,
  updateEmployee,
  deleteEmployee,
} from "../api";

function AdminEmployees() {
  const [employees, setEmployees] = useState([]);
  const [form, setForm] = useState({ name: "", email: "", role: "EMPLOYEE" });
  const [updateID, setupdateID] = useState(null);

  const load = async () => {
    try {
      const data = await getEmployees();
      setEmployees(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error("Failed to load employees:", err);
      setEmployees([]);
    }
  };

  useEffect(() => {
    load();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (updateID) {
        await updateEmployee(updateID, form);
      } else {
        await createEmployee(form);
      }
      setForm({ name: "", email: "", role: "EMPLOYEE" });
      setupdateID(null);
      load();
    } catch (err) {
      console.error("Save employee failed:", err);
      alert("Failed to save employee");
    }
  };

  const handleEdit = (emp) => {
    setupdateID(emp.id);
    setForm({ name: emp.name, email: emp.email, role: emp.role });
  };

  const handleDelete = async (id) => {
    if (window.confirm("Delete employee?")) {
      try {
        await deleteEmployee(id);
        load();
      } catch (err) {
        console.error("Delete employee failed:", err);
        alert(
          "Failed to delete employee because the employee has been assigned to review"
        );
      }
    }
  };

  return (
    <div>
      <h3>Admin - Employees</h3>

      <form onSubmit={handleSubmit} style={{ marginBottom: 20 }}>
        <input
          placeholder="Name"
          value={form.name}
          onChange={(e) => setForm((f) => ({ ...f, name: e.target.value }))}
          required
        />
        <input
          placeholder="Email"
          type="email"
          value={form.email}
          onChange={(e) => setForm((f) => ({ ...f, email: e.target.value }))}
          required
        />
        <select
          value={form.role}
          onChange={(e) => setForm((f) => ({ ...f, role: e.target.value }))}
        >
          <option value="EMPLOYEE">EMPLOYEE</option>
          <option value="ADMIN">ADMIN</option>
        </select>
        <button type="submit">
          {updateID ? "Update Employee" : "Add Employee"}
        </button>
        {updateID && (
          <button
            type="button"
            onClick={() => {
              setupdateID(null);
              setForm({ name: "", email: "", role: "EMPLOYEE" });
            }}
          >
            Cancel
          </button>
        )}
      </form>

      <table border="1" cellPadding="4">
        <thead>
          <tr>
            <th>Employee ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
            <th>Dashboard</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((emp) => (
            <tr key={emp.id}>
              <td>{emp.id}</td>
              <td>{emp.name}</td>
              <td>{emp.email}</td>
              <td>{emp.role}</td>
              <td>
                <button onClick={() => handleEdit(emp)}>Edit</button>
                <button onClick={() => handleDelete(emp.id)}>Delete</button>
              </td>
              <td>
                <Link to={`/employee/${emp.id}`}>Open Dashboard</Link>
              </td>
            </tr>
          ))}
          {!employees.length && (
            <tr>
              <td colSpan="6">No employees yet.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default AdminEmployees;
