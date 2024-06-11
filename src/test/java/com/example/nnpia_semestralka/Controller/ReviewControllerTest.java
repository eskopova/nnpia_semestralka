package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Dto.ReviewDto;
import com.example.nnpia_semestralka.Entity.Review;
import com.example.nnpia_semestralka.Service.ConversionService;
import com.example.nnpia_semestralka.Service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    void testGetAllReviews() throws Exception {
        Review review1 = new Review();
        review1.setId(1L);
        review1.setRating(5);
        review1.setComment("Great product!");

        Review review2 = new Review();
        review2.setId(2L);
        review2.setRating(4);
        review2.setComment("Good product!");

        ReviewDto reviewDto1 = new ReviewDto();
        reviewDto1.setId(1L);
        reviewDto1.setRating(5);
        reviewDto1.setComment("Great product!");

        ReviewDto reviewDto2 = new ReviewDto();
        reviewDto2.setId(2L);
        reviewDto2.setRating(4);
        reviewDto2.setComment("Good product!");

        List<Review> reviews = Arrays.asList(review1, review2);
        Page<Review> reviewPage = new PageImpl<>(reviews, PageRequest.of(0, 4, Sort.by("id")), reviews.size());

        when(reviewService.getAllReviews(any(Integer.class), any(Integer.class), any(String.class))).thenReturn(reviewPage);
        when(conversionService.toReviewDto(review1)).thenReturn(reviewDto1);
        when(conversionService.toReviewDto(review2)).thenReturn(reviewDto2);

        mockMvc.perform(get("/reviews")
                        .param("pageNumber", "0")
                        .param("pageSize", "4")
                        .param("sortBy", "id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].rating").value(5))
                .andExpect(jsonPath("$[0].comment").value("Great product!"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].rating").value(4))
                .andExpect(jsonPath("$[1].comment").value("Good product!"));
    }

    @Test
    void testAddReview() throws Exception {
        Review review = new Review();
        review.setId(1L);
        review.setRating(5);
        review.setComment("Great product!");

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setRating(5);
        reviewDto.setComment("Great product!");

        when(reviewService.addReview(any(Review.class))).thenReturn(review);
        when(conversionService.toReview(any(ReviewDto.class))).thenReturn(review);
        when(conversionService.toReviewDto(review)).thenReturn(reviewDto);

        mockMvc.perform(post("/reviews")
                        .contentType("application/json")
                        .content("{ \"rating\": 5, \"comment\": \"Great product!\", \"userId\": 1, \"productId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.comment").value("Great product!"));
    }
}
