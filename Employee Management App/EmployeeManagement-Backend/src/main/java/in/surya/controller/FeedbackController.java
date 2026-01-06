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

import in.surya.dto.FeedBackRequest;
import in.surya.entity.feedBack;
import in.surya.service.FeedBackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private FeedBackService service;
	
	@PostMapping("/reviews/{reviewId}")
    public ResponseEntity<?> submit(@PathVariable int reviewId,
                                    @RequestBody FeedBackRequest request) {

        return service.submitFeedback(reviewId, request)
                .map(savedFeedback -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Feedback submitted successfully");
                    response.put("status", true);
                    response.put("data", savedFeedback);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Failed to submit feedback (invalid review/reviewer)");
                    response.put("status", false);
                    response.put("data", null);
                    return ResponseEntity.ok(response);
                });
    }
	@GetMapping("/all")
	public List<feedBack> getAllFeedback() {
	    return service.getAllFeedBack();
	}
}
