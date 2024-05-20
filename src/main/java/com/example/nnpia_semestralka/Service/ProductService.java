package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product findById(Long id);

    public Product save(Product product);

    public Product update(Long id, Product product);

    public void deleteById(Long id);
}
