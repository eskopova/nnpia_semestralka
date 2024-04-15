package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
