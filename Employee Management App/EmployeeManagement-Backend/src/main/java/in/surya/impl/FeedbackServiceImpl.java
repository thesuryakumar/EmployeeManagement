package in.surya.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.surya.dto.FeedBackRequest;
import in.surya.entity.Employee;
import in.surya.entity.PerformanceReview;
import in.surya.entity.feedBack;
import in.surya.repo.EmployeeRepo;
import in.surya.repo.FeedbackRepo;
import in.surya.repo.PerformaneRepo;
import in.surya.repo.ReviewRepo;
import in.surya.service.FeedBackService;
@Service
public class FeedbackServiceImpl implements FeedBackService {
	@Autowired
	private PerformaneRepo prepo;
	@Autowired
	private EmployeeRepo erepo;
	@Autowired
	private ReviewRepo rrepo;
	@Autowired
	private FeedbackRepo repo;
	@Override
	public Optional<feedBack> submitFeedback(int rId, FeedBackRequest req) {
		Optional<PerformanceReview> reviewOpt = prepo.findById(rId);
        Optional<Employee> reviewerOpt = erepo.findById(req.getRid());

        if (reviewOpt.isEmpty() || reviewerOpt.isEmpty()) {
            return Optional.empty();
        }

        feedBack feedback = new feedBack(
                req.getRating(),
                req.getFeedback(),
                reviewOpt.get(),
                reviewerOpt.get()
        );
        feedBack saved = repo.save(feedback);

        rrepo.findAll().stream()
                .filter(a -> a.getReview().getId()==(rId)
                        && a.getReviwer().getId()==(req.getRid()))
                .forEach(a -> {
                    a.setStatus("COMPLETED");
                    rrepo.save(a);
                });

        return Optional.of(saved);
	}
	@Override
	public List<feedBack> getAllFeedBack() {
		
		return repo.findAll();
	}

}
