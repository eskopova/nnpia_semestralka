package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByProductNameContains(String contains);

    List<Product> findByShop(String shop);
}
