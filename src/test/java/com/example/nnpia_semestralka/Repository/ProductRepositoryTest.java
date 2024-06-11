package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.ProductCategory;
import com.example.nnpia_semestralka.Entity.Shop;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.example.nnpia_semestralka")
@EnableJpaRepositories(basePackages = "com.example.nnpia_semestralka.Repository")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveProductSuccessTest() {
        Shop shop = new Shop();
        shop.setShopName("Test Shop");

        Product product = new Product();
        product.setProductName("Test Product");
        product.setPathToImage("path/to/image");
        product.setDescription("Test Description");
        product.setCategory(ProductCategory.Obyčejné);
        product.setShop(shop);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId(), "Product ID should not be null");
        assertEquals("Test Product", savedProduct.getProductName(), "Product name should be 'Test Product'");
        assertEquals("path/to/image", savedProduct.getPathToImage(), "Path to image should be 'path/to/image'");
        assertEquals("Test Description", savedProduct.getDescription(), "Description should be 'Test Description'");
        assertEquals(ProductCategory.Obyčejné, savedProduct.getCategory(), "Category should be 'Obyčejné'");
        assertEquals(shop, savedProduct.getShop(), "Shop should be 'Test Shop'");
    }

    @Test
    void saveProductFailureTest() {
        Product product = new Product();

        assertThrows(ConstraintViolationException.class, () -> {
            productRepository.saveAndFlush(product);
        }, "Expected a ConstraintViolationException to be thrown, but it wasn't");
    }
}