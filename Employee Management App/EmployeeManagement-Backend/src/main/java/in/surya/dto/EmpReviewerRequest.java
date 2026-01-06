package in.surya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// assigning employee to review
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpReviewerRequest {
	private Integer reviewerId; 
}
