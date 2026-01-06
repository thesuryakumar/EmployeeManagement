package in.surya.service;

import java.util.List;
import java.util.Optional;


import in.surya.dto.FeedBackRequest;
import in.surya.entity.feedBack;

public interface FeedBackService {
	Optional<feedBack> submitFeedback(int rId, FeedBackRequest req);
	List<feedBack> getAllFeedBack();
}
