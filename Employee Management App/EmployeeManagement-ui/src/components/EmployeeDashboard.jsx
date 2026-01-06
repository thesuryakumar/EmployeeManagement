import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getAssignedReviews, submitFeedback } from "../api";

function EmployeeDashboard() {
  const { id } = useParams();
  const [assignments, setAssignments] = useState([]);
  const [selectedReviewId, setSelectedReviewId] = useState(null);
  const [feedbackForm, setFeedbackForm] = useState({ rating: 5, comments: "" });

  const loadAssignments = async () => {
    const data = await getAssignedReviews(id);
    setAssignments(Array.isArray(data) ? data : []);
  };

  useEffect(() => {
    loadAssignments();
  }, [id]);

  const openFeedback = (assignment) => {
    setSelectedReviewId(assignment.review.id);
    setFeedbackForm({ rating: 5, comments: "" });
  };

  const handleSubmitFeedback = async (e) => {
    e.preventDefault();
    await submitFeedback(selectedReviewId, {
      reviewerId: Number(id),
      rating: Number(feedbackForm.rating),
      comments: feedbackForm.comments,
    });
    alert("Feedback submitted");
    setSelectedReviewId(null);
    loadAssignments();
  };

  return (
    <div>
      <h3>Employee Dashboard - ID - {id}</h3>
      <h4>Reviews requiring your feedback</h4>
      <ul>
        {assignments.map((a) => (
          <li key={a.id}>
            Review #{a.review.id} - {a.review.title} (for{" "}
            {a.review.performanceReview?.name})
            <button onClick={() => openFeedback(a)} style={{ marginLeft: 10 }}>
              Give Feedback
            </button>
          </li>
        ))}
        {!assignments.length && <li>You have no pending reviews assigned.</li>}
      </ul>

      {selectedReviewId && (
        <div style={{ marginTop: 20 }}>
          <h4>Submit Feedback for Review #{selectedReviewId}</h4>
          <form onSubmit={handleSubmitFeedback}>
            <label>
              Rating (1-5):{" "}
              <input
                type="number"
                min="1"
                max="5"
                value={feedbackForm.rating}
                onChange={(e) =>
                  setFeedbackForm((f) => ({ ...f, rating: e.target.value }))
                }
                required
              />
            </label>
            <br />
            <textarea
              placeholder="Comments"
              value={feedbackForm.comments}
              onChange={(e) =>
                setFeedbackForm((f) => ({ ...f, comments: e.target.value }))
              }
              required
              rows="4"
              cols="50"
            />
            <br />
            <button type="submit">Submit Feedback</button>
            <button type="button" onClick={() => setSelectedReviewId(null)}>
              Cancel
            </button>
          </form>
        </div>
      )}
    </div>
  );
}

export default EmployeeDashboard;
