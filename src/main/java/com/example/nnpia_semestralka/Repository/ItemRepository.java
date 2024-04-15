package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<OrderItem, Long> {
}
