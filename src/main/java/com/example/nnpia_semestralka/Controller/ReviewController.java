package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Dto.ReviewDto;
import com.example.nnpia_semestralka.Entity.Review;
import com.example.nnpia_semestralka.Service.ConversionService;
import com.example.nnpia_semestralka.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    @Autowired
    private final ConversionService conversionService;

    public ReviewController(ReviewService reviewService, ConversionService conversionService) {
        this.reviewService = reviewService;
        this.conversionService = conversionService;
    }

    @GetMapping("")
    public List<ReviewDto> getAllReviews(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return reviewService.getAllReviews(pageNumber, pageSize, sortBy)
                .stream()
                .map(conversionService::toReviewDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return conversionService.toReviewDto(reviewService.getReviewById(id));
    }

    @GetMapping("/by-product/{id}")
    public List<ReviewDto> getReviewByProductId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return reviewService.getReviewByProductId(id, pageNumber, pageSize, sortBy)
                .stream()
                .map(conversionService::toReviewDto)
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public ReviewDto addReview(@RequestBody ReviewDto ReviewDto) {
        Review review = conversionService.toReview(ReviewDto);
        return conversionService.toReviewDto(reviewService.addReview(review));
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}