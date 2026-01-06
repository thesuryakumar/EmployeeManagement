package in.surya.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackRequest {
	private Integer rid;// reviewer
	private double rating;
	private String feedback;
}
