package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByProductId(long productId, PageRequest pageable);
}
