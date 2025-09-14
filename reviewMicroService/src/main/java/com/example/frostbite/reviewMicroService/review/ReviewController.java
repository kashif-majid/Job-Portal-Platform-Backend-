package com.example.frostbite.reviewMicroService.review;


import com.example.frostbite.reviewMicroService.review.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findAllReviews(@RequestParam  Long companyId){
        return new ResponseEntity<>(reviewService.findAllReviews(companyId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReviews(@RequestParam Long companyId,@RequestBody Review review){
        boolean isReviewSaved = reviewService.createReviews(companyId,review);
        if(isReviewSaved) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review Successfully Created", HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Review Not Created", HttpStatus.NOT_FOUND);

        }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewsById(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewsById(reviewId),HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviews(@PathVariable Long reviewId){
        boolean deleted = reviewService.deleteReviews(reviewId);
        if(deleted)
            return new ResponseEntity<>("Review Successfully Deleted",HttpStatus.OK);
        return new ResponseEntity<>("Review Not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviews(@PathVariable Long reviewId,@RequestBody Review review){
        boolean updated = reviewService.updateReviews(reviewId,review);
        if(updated)
            return new ResponseEntity<>("Review Successfully Updated",HttpStatus.OK);
        return new ResponseEntity<>("Review Not Found",HttpStatus.NOT_FOUND);

    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){
        System.out.println("rating");

        List<Review> reviewList = reviewService.findAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }


}
