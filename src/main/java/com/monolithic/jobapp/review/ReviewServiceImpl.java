package com.monolithic.jobapp.review;

import com.monolithic.jobapp.company.Company;
import com.monolithic.jobapp.company.CompanyRepository;
import com.monolithic.jobapp.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @Autowired
    ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId).get();
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

//       return this.companyService.findById(companyId)
//                .map(
//                        company -> {
//                            Company companyObj = (Company)company;
//                            review.setCompany(company);
//                            reviewRepository.save(review);
//                            return true;
//                        }
//                ).orElse(false);

        return this.companyRepository.findById(companyId)
                .map(company -> {
                    review.setCompany(company);
                    reviewRepository.save(review);
                    return true;
                })
                .orElse(false);

//        if(this.companyService.findById(companyId) !=null){
//            Company company = this.companyService.findById(companyId);
//            review.setCompany(company);
//            reviewRepository.save(review);
//            return true;
//        }
//        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        Optional<List<Review>> reviews = this.reviewRepository.findByCompanyId(companyId);
        if(reviews.isPresent()){
            List<Review> reviewList = reviews.get();
            return  reviewList.stream()
                    .filter(r -> r.getId().equals(reviewId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public boolean updateReview(Long companyId, Review updatedReview, Long reviewId) {
        if(this.companyRepository.findById(companyId) != null){
            updatedReview.setCompany(this.companyRepository.findById(companyId).get());
            updatedReview.setId(reviewId);
            this.reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(this.companyRepository.findById(companyId) != null && this.reviewRepository.findById(reviewId) != null){
        Review review = this.reviewRepository.findById(reviewId).get();
        Company company = review.getCompany();
        company.getReviews().remove(review);
        review.setCompany(null);
        this.companyService.updateCompany(companyId, company);
        this.reviewRepository.delete(review);
        return true;
        }
        return false;
    }
}
