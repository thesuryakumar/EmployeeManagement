package in.surya.service;

import java.util.List;
import java.util.Optional;

import in.surya.dto.EmpReviewerRequest;
import in.surya.dto.ReviewRequest;
import in.surya.entity.PerformanceReview;
import in.surya.entity.Review;

public interface PerformanceReviewService {
	List<PerformanceReview> getAllReviews();
    Optional<PerformanceReview> getReviewById(int id);
    Optional<PerformanceReview> createReview(ReviewRequest req);
    Optional<Review> assignReviewer(int rId, EmpReviewerRequest req);

}
