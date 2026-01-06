package in.surya.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.surya.entity.Employee;
import in.surya.entity.Review;
import in.surya.repo.EmployeeRepo;
import in.surya.repo.ReviewRepo;
import in.surya.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private EmployeeRepo erepo;
	@Autowired
	private ReviewRepo rrepo;
	@Override
	public Optional<List<Review>> getPendingAssignmentsForEmployee(int empId) {
		Optional<Employee> empOpt = erepo.findById(empId);
        if (empOpt.isEmpty()) {
            return Optional.empty();
        }
        List<Review
        > assignments =
                rrepo.findByReviwerAndStatus(empOpt.get(), "PENDING");
        return Optional.of(assignments);
	}

}
