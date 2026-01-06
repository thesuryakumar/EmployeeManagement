const API_BASE = import.meta.env.VITE_API_URL;

async function handler(res) {
  if (!res.ok) {
    const text = await res.text().catch(() => "");
    console.error("HTTP error", res.status, text);
    throw new Error(`Request failed with status ${res.status}`);
  }
  return res.json();
}

export async function getEmployees() {
  const res = await fetch(`${API_BASE}/employee`);
  return handler(res);
}

export async function createEmployee(employee) {
  const res = await fetch(`${API_BASE}/employee`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(employee),
  });
  return handler(res);
}

export async function updateEmployee(id, employee) {
  const res = await fetch(`${API_BASE}/employee/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(employee),
  });
  return handler(res);
}

export async function deleteEmployee(id) {
  const res = await fetch(`${API_BASE}/employee/${id}`, {
    method: "DELETE",
  });
  return handler(res);
}

export async function getReviews() {
  const res = await fetch(`${API_BASE}/performance_review`);
  return handler(res);
}

export async function createReview(form) {
  const payload = {
    title: form.title,
    date: form.dueDate, // yyyy-MM-dd
    employeeId: Number(form.revieweeId),
  };

  const res = await fetch(`${API_BASE}/performance_review`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  const json = await handler(res);

  if (json.status === false) {
    throw new Error(json.message || "Failed to create review");
  }

  return json;
}

export async function assignReviewer(reviewId, reviewerId) {
  const payload = { reviewerId: Number(reviewerId) };

  const res = await fetch(`${API_BASE}/performance_review/${reviewId}/assign`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  const json = await handler(res);
  console.log("assignReviewer -> response:", json);

  if (json.status === false) {
    throw new Error(json.message || "Failed to assign reviewer");
  }

  return json;
}

export async function getAssignedReviews(employeeId) {
  const res = await fetch(`${API_BASE}/review/${employeeId}/assigned-reviews`);
  const json = await handler(res);
  return json.data ?? [];
}

export async function submitFeedback(reviewId, payload) {
  const body = {
    rid: Number(payload.reviewerId),
    rating: Number(payload.rating),
    feedback: payload.comments,
  };

  const res = await fetch(`${API_BASE}/feedback/reviews/${reviewId}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });

  return handler(res);
}
export async function getAllFeedback() {
  const res = await fetch(`${API_BASE}/feedback/all`);
  const json = await handler(res);
  return json.data ?? json;
}
