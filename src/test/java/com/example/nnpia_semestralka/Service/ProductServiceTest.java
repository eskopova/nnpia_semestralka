package com.example.nnpia_semestralka.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.Review;
import com.example.nnpia_semestralka.Exceptions.NotFoundException;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateProductRating_OneReview() {
        Product product = new Product();
        product.setId(1L);

        Review review1 = new Review();
        review1.setRating(10);

        Set<Review> reviews = new HashSet<>();
        reviews.add(review1);

        product.setProductReviews(reviews);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        double rating = productService.getProductRating(1L);

        assertEquals(10, rating);

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testCalculateProductRating_TwoReviews() {
        Product product = new Product();
        product.setId(1L);

        Review review1 = new Review();
        review1.setRating(4);

        Review review2 = new Review();
        review2.setRating(5);

        Set<Review> reviews = new HashSet<>();
        reviews.add(review1);
        reviews.add(review2);

        product.setProductReviews(reviews);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        double rating = productService.getProductRating(1L);

        assertEquals(4.5, rating);

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testCalculateProductRating_NoReviews() {
        Product product = new Product();
        product.setId(1L);
        product.setProductReviews(new HashSet<>());

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        double rating = productService.getProductRating(1L);

        assertEquals(0.0, rating);

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testCalculateProductRating_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            productService.getProductRating(1L);
        } catch (NotFoundException e) {
            assertEquals("Produkt nenalezen", e.getMessage());
        }

        verify(productRepository, times(1)).findById(1L);
    }
}
