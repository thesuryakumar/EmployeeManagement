package in.surya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
	private String title;
	private String date; //yyyy-mm-dd
	private Integer employeeId; 
}
