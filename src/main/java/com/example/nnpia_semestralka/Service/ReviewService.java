package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Dto.ReviewDto;
import com.example.nnpia_semestralka.Entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    Page<Review> getAllReviews(Integer page, Integer size, String sortBy);

    Review getReviewById(Long id);

    Page<Review> getReviewByProductId(Long id, Integer page, Integer size, String sortBy);

    Review addReview(Review review);

    void deleteReview(Long id);
}
