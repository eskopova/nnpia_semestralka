package com.example.nnpia_semestralka;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveProductTest() {
        Product product = new Product();
        product.setProductName("Maki");

        productRepository.save(product);

        boolean isSaved = productRepository.existsById(product.getId());
        assert(isSaved);
    }
}
