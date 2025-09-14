package com.example.frostbite.reviewMicroService.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews(Long companyId);
    boolean createReviews(Long companyId,Review review);
    Review getReviewsById(Long reviewId);
    boolean deleteReviews(Long reviewId);
    boolean updateReviews(Long reviewId,Review updatedReviews);

}
