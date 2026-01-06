package in.surya.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.surya.entity.PerformanceReview;
import in.surya.entity.feedBack;

public interface FeedbackRepo extends JpaRepository<feedBack, Integer> {
	List<feedBack> findByReview(PerformanceReview review);
}
