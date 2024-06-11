package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<Product> findAll(Integer page, Integer size, String sortBy);

    Product findById(Long id);

    Page<Product> findByCategory(String categoryName, Integer page, Integer size, String sortBy);

    Page<Product> findBySubstring(String substring, Integer page, Integer size, String sortBy);

    Product save(Product product);

    Product update(Long id, Product product);

    void deleteById(Long id);

    double getProductRating(Long id);
}
