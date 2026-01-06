package in.surya.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import in.surya.entity.Employee;
import in.surya.entity.Review;
import java.util.List;


public interface ReviewRepo extends JpaRepository<Review, Integer> {
	 List<Review> findByReviwerAndStatus(Employee emp,String status);

}
