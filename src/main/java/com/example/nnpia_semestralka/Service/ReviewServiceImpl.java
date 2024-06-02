package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Dto.ReviewDto;
import com.example.nnpia_semestralka.Entity.AppUser;
import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.Review;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import com.example.nnpia_semestralka.Repository.ReviewRepository;
import com.example.nnpia_semestralka.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Page<Review> getAllReviews(Integer page, Integer size, String sortBy) {
        PageRequest request = PageRequest.of(page, size, Sort.Direction.ASC, sortBy);
        return reviewRepository.findAll(request);
    }

    public Page<Review> getReviewByProductId(Long id, Integer page, Integer size, String sortBy) {
        PageRequest request = PageRequest.of(page, size, Sort.Direction.ASC, sortBy);
        return reviewRepository.findByProductId(id, request);
    }


    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review addReview(Review review) {
        review.setReviewDate(Timestamp.from(Instant.now()));
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
