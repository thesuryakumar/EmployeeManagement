import React, { useEffect, useState } from "react";
import { getAllFeedback } from "../api";

function Feedback() {
  const [feedbackList, setFeedbackList] = useState([]);

  const loadFeedback = async () => {
    try {
      const data = await getAllFeedback();
      setFeedbackList(Array.isArray(data) ? data : []);
      console.log(data);
    } catch (err) {
      console.error("Failed to load feedback:", err);
      setFeedbackList([]);
    }
  };

  useEffect(() => {
    loadFeedback();
  }, []);

  return (
    <div>
      <h3>All Submitted Feedback</h3>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>ID</th>
            <th>Review Date</th>
            <th>Title</th>
            <th>Reviewer ID</th>
            <th>Reviewee name</th>
            <th>Reviewee ID</th>
            <th>Rating</th>
            <th>FeedBack</th>
          </tr>
        </thead>
        <tbody>
          {feedbackList.map((fb) => (
            <tr key={fb.id}>
              <td>{fb.id}</td>
              <td>{fb.date ?? "Unknown"}</td>
              <td>{fb.review.title ?? "Unknown"}</td>
              <td>{fb.review.id ?? "Unknown"}</td>
              <td>{fb.reviwer.name ?? "Unknown"}</td>
              <td>{fb.reviwer.id ?? "Unknown"}</td>
              <td>{fb.rating ?? "Unknown"}</td>
              <td>{fb.feedback ?? "Unknown"}</td>
            </tr>
          ))}
          {!feedbackList.length && (
            <tr>
              <td colSpan="7">No feedback submitted yet.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Feedback;
