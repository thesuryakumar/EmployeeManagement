import React, { useEffect, useState } from "react";
import { getEmployees, getReviews, createReview, assignReviewer } from "../api";

function Reviews() {
  const [employees, setEmployees] = useState([]);
  const [reviews, setReviews] = useState([]);
  const [form, setForm] = useState({
    title: "",
    dueDate: "",
    revieweeId: "",
  });

  const [assignForm, setAssignForm] = useState({
    reviewId: "",
    reviewerId: "",
  });

  const loadAll = async () => {
    try {
      const [emps, revs] = await Promise.all([getEmployees(), getReviews()]);
      setEmployees(Array.isArray(emps) ? emps : []);
      setReviews(Array.isArray(revs) ? revs : []);
    } catch (err) {
      console.error("Failed to load employees or reviews:", err);
      alert("Failed to load data from server. Check console for details.");
    }
  };

  useEffect(() => {
    loadAll();
  }, []);

  const handleCreateReview = async (e) => {
    e.preventDefault();
    try {
      await createReview(form);
      alert("Review created successfully");
      setForm({ title: "", dueDate: "", revieweeId: "" });
      await loadAll();
    } catch (err) {
      console.error("Create review failed:", err);
      alert(err.message || "Failed to create review");
    }
  };

  const handleAssign = async (e) => {
    e.preventDefault();
    if (!assignForm.reviewId || !assignForm.reviewerId) {
      alert("Please select both review and reviewer.");
      return;
    }

    try {
      await assignReviewer(assignForm.reviewId, assignForm.reviewerId);
      alert("Reviewer assigned successfully.");
      setAssignForm({ reviewId: "", reviewerId: "" });
      await loadAll();
    } catch (err) {
      console.error("Assign reviewer failed:", err);
      alert(err.message || "Failed to assign reviewer");
    }
  };

  return (
    <div>
      <h3>Reviews</h3>
      <h4>Create Performance Review</h4>
      <form onSubmit={handleCreateReview}>
        <input
          placeholder="Title"
          value={form.title}
          onChange={(e) => setForm((f) => ({ ...f, title: e.target.value }))}
          required
        />
        <input
          type="date"
          value={form.dueDate}
          onChange={(e) => setForm((f) => ({ ...f, dueDate: e.target.value }))}
          required
        />
        <select
          value={form.revieweeId}
          onChange={(e) =>
            setForm((f) => ({ ...f, revieweeId: e.target.value }))
          }
          required
        >
          <option value="">Select Reviewee</option>
          {employees.map((emp) => (
            <option key={emp.id} value={emp.id}>
              {emp.name} ({emp.email})
            </option>
          ))}
        </select>
        <button type="submit">Create Review</button>
      </form>

      <h4 style={{ marginTop: 20 }}>Assign Reviewer</h4>
      <form onSubmit={handleAssign}>
        <select
          value={assignForm.reviewId}
          onChange={(e) =>
            setAssignForm((f) => ({ ...f, reviewId: e.target.value }))
          }
        >
          <option value="">Select Review</option>
          {reviews.map((rev) => (
            <option key={rev.id} value={rev.id}>
              {rev.id} - {rev.title} (reviewee:{" "}
              {rev.performanceReview?.name || "N/A"})
            </option>
          ))}
        </select>

        <select
          value={assignForm.reviewerId}
          onChange={(e) =>
            setAssignForm((f) => ({ ...f, reviewerId: e.target.value }))
          }
        >
          <option value="">Select Reviewer</option>
          {employees.map((emp) => (
            <option key={emp.id} value={emp.id}>
              {emp.name} ({emp.email})
            </option>
          ))}
        </select>
        <button type="submit">Assign</button>
      </form>

      <h4 style={{ marginTop: 20 }}>All Reviews</h4>
      {reviews.length > 0 ? (
        <table
          border="1"
          cellPadding="8"
          style={{ width: "100%", marginTop: 10 }}
        >
          <thead>
            <tr>
              <th>Id</th>
              <th>Title</th>
              <th>Reviewee Name</th>
              <th>Due Date</th>
              <th>Status</th>
            </tr>
          </thead>

          <tbody>
            {reviews.map((r) => (
              <tr key={r.id}>
                <td>{r.id}</td>
                <td>{r.title}</td>
                <td>{r.performanceReview?.name || "N/A"}</td>
                <td>{r.dueDate}</td>
                <td>{r.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No reviews yet.</p>
      )}
    </div>
  );
}

export default Reviews;
