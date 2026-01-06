package in.surya.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name="review_id")
	private PerformanceReview review;
	
	@ManyToOne
	@JoinColumn(name="reviwer_id")
	private Employee reviwer;
	private String status;
	public Review(PerformanceReview review, Employee reviwer, String status) {
		super();
		this.review = review;
		this.reviwer = reviwer;
		this.status = status;
	}
	
}
