package in.surya.impl;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.surya.dto.EmpReviewerRequest;
import in.surya.dto.ReviewRequest;
import in.surya.entity.Employee;
import in.surya.entity.PerformanceReview;
import in.surya.entity.Review;
import in.surya.repo.EmployeeRepo;
import in.surya.repo.PerformaneRepo;
import in.surya.repo.ReviewRepo;
import in.surya.service.PerformanceReviewService;
@Service
public class PerformanceReviewServiceImpl implements PerformanceReviewService {
	@Autowired
	private PerformaneRepo repo;
	@Autowired
	private EmployeeRepo erepo;
	@Autowired
	private ReviewRepo rrepo;
	@Override
	public List<PerformanceReview> getAllReviews() {
		return repo.findAll();
	}

	@Override
	public Optional<PerformanceReview> getReviewById(int id) {
		return repo.findById(id);
	}

	@Override
	public Optional<PerformanceReview> createReview(ReviewRequest req) {
		if (req.getEmployeeId() == null) {
	        return Optional.empty();
	    }

	    Optional<Employee> empOpt = erepo.findById(req.getEmployeeId());
	    if (empOpt.isEmpty()) {
	        return Optional.empty();
	    }

	    Employee reviewee = empOpt.get();

	    PerformanceReview review = new PerformanceReview();
	    review.setTitle(req.getTitle());
	    review.setPerformanceReview(reviewee);
	    review.setStatus("OPEN");
	    review.setDueDate(LocalDate.parse(req.getDate()));

	    return Optional.of(repo.save(review));
	}

	@Override
	public Optional<Review> assignReviewer(int rId, EmpReviewerRequest req) {
		if (req == null || req.getReviewerId() == null) {  
	        return Optional.empty();
	    }
	    Optional<PerformanceReview> reviewOpt = repo.findById(rId);
	    Optional<Employee> reviewerOpt = erepo.findById(req.getReviewerId());

	    if (reviewOpt.isEmpty() || reviewerOpt.isEmpty()) {
	        return Optional.empty();
	    }
	    Review assignment = new Review(
	            reviewOpt.get(),
	            reviewerOpt.get(),
	            "PENDING"
	    );

	    Review saved = rrepo.save(assignment);
	    return Optional.of(saved);
	}

}
