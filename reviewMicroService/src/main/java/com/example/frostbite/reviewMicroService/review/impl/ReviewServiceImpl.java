package com.example.frostbite.reviewMicroService.review.impl;


import com.example.frostbite.reviewMicroService.review.Review;
import com.example.frostbite.reviewMicroService.review.ReviewRepository;
import com.example.frostbite.reviewMicroService.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
            public boolean createReviews(Long companyId,Review review) {
        if(companyId!=null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else
            return false;
    }

    @Override
    public Review getReviewsById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean deleteReviews(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!= null){

            reviewRepository.delete(review);
            return true;
        }else
            return false;
    }

    @Override
    public boolean updateReviews(Long reviewId, Review updatedReviews) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updatedReviews.getTitle());
            review.setDescription(updatedReviews.getDescription());
            review.setRating(updatedReviews.getRating());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }
}
