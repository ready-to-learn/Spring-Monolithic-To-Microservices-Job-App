package com.monolithic.jobapp.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {


    List<Review> getReviews(Long companyId);
    boolean addReview(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Review review, Long reviewId);

    boolean deleteReview(Long companyId, Long reviewId);
}
