package in.surya.service;

import java.util.List;
import java.util.Optional;

import in.surya.entity.Review;

public interface ReviewService {
	Optional<List<Review>> getPendingAssignmentsForEmployee(int empId);
}
