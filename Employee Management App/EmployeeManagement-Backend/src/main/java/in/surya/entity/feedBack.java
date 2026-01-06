package in.surya.entity;


import java.time.LocalDate;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class feedBack {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double rating;
	private String feedback;
	private LocalDate date = LocalDate.now();
	
	@ManyToOne
	@JoinColumn(name="review_id")
	private PerformanceReview review;
	
	@ManyToOne
	@JoinColumn(name="reviwer_id")
	private Employee reviwer;

	public feedBack(double rating, String feedback, PerformanceReview review, Employee reviwer) {
		super();
		this.rating = rating;
		this.feedback = feedback;
		this.review = review;
		this.reviwer = reviwer;
	}
	
}
