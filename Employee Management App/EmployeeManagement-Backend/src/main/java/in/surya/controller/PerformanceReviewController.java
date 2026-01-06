package in.surya.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.surya.dto.EmpReviewerRequest;
import in.surya.dto.ReviewRequest;
import in.surya.entity.PerformanceReview;
import in.surya.service.PerformanceReviewService;

@RestController
@RequestMapping("/performance_review")
public class PerformanceReviewController {
	@Autowired
	private PerformanceReviewService service;
	@GetMapping
    public List<PerformanceReview> getAll() {
        return service.getAllReviews();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReviewRequest request) {
    	return service.createReview(request)
                .map(created -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Review created successfully");
                    response.put("status", true);
                    response.put("data", created);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Failed to create review (invalid reviewee?)");
                    response.put("status", false);
                    response.put("data", null);
                    return ResponseEntity.ok(response);
                });
    }

    @PostMapping("/{reviewId}/assign")
    public ResponseEntity<?> assignReviewer(@PathVariable int reviewId,
                                                           @RequestBody EmpReviewerRequest req) 
    {
    	var result = service.assignReviewer(reviewId, req);

        Map<String, Object> response = new HashMap<>();
        if (result.isPresent()) {
            response.put("message", "Reviewer assigned successfully");
            response.put("status", true);
            response.put("data", result.get());
        } else {
            response.put("message", "Invalid reviewId or reviewerId");
            response.put("status", false);
        }

        return ResponseEntity.ok(response);
    }
	
}
