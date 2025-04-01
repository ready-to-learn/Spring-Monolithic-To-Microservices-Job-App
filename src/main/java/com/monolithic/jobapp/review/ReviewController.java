package com.monolithic.jobapp.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getReviews(companyId);
        if (reviews.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review) {
        boolean isAdded = this.reviewService.addReview(companyId, review);
        if(isAdded){
            return new ResponseEntity<>("Review added", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Review not added", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = this.reviewService.getReviewById(companyId,reviewId);
        if(review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isUpdated = this.reviewService.updateReview(companyId,review,reviewId);
        if(isUpdated)
            return new ResponseEntity<>("Review updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not updated", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = this.reviewService.deleteReview(companyId,reviewId);
        if(isDeleted)
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not deleted", HttpStatus.BAD_REQUEST);
    }


}
