package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.ProductCategory;
import com.example.nnpia_semestralka.Entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByProductNameContains(String contains, PageRequest pageRequest);

    Page<Product> findByCategory(ProductCategory category, PageRequest pageRequest);

    Page<Product> findByShop(Shop shop, PageRequest pageRequest);
}
