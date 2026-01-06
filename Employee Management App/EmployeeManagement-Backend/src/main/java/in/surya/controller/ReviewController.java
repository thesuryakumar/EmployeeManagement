package in.surya.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.surya.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService service;
	
	 @GetMapping("/{eId}/assigned-reviews")
	    public ResponseEntity<?> getAssigned(@PathVariable int eId) {

	        return service.getPendingAssignmentsForEmployee(eId)
	                .map(assignments -> {
	                    Map<String, Object> response = new HashMap<>();
	                    response.put("message", "Assigned reviews fetched successfully");
	                    response.put("status", true);
	                    response.put("data", assignments);
	                    return ResponseEntity.ok(response);
	                })
	                .orElseGet(() -> {
	                    Map<String, Object> response = new HashMap<>();
	                    response.put("message", "Employee not found or no pending assignments");
	                    response.put("status", false);
	                    response.put("data", null);
	                    return ResponseEntity.ok(response);
	                });
	    }
}
