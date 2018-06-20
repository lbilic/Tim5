package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Review;
import ftn.isamrs.tim5.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public boolean alreadyVoted(Long accountId, Long cineterId) {

        return this.reviewRepository.alreadyVoted(accountId, cineterId) == null;
    }

    @Override
    public Review save(Review review) {
        return this.reviewRepository.save(review);
    }

    @Override
    public List<Review> findByCineterId(Long cineterId) {
        return this.reviewRepository.findByCinterId(cineterId);
    }

    @Override
    public List<Review> findByShowId(Long id) {
        return this.reviewRepository.findByShowId(id);
    }
}
