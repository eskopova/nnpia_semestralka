package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.ProductCategory;
import com.example.nnpia_semestralka.Exceptions.NotFoundException;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Integer page, Integer size, String sortBy) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, sortBy));
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produkt nenalezen"));
    }

    public Page<Product> findByCategory(String categoryName, Integer page, Integer size, String sortBy) {
        var pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, sortBy);
        var productCategory = ProductCategory.valueOf(categoryName);
        return productRepository.findByCategory(productCategory, pageRequest);
    }

    public Page<Product> findBySubstring(String substring, Integer page, Integer size, String sortBy) {
        var pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, sortBy);
        return productRepository.findByProductNameContains(substring, pageRequest);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product byId = findById(id);

        if (product.getDescription().isBlank()) {
            product.setDescription(byId.getDescription());
        }
        if (product.getProductName().isBlank()) {
            product.setProductName(byId.getProductName());
        }
        if (product.getPathToImage().isBlank()) {
            product.setPathToImage(byId.getPathToImage());
        }
        if (product.getShop() == null) {
            product.setShop(byId.getShop());
        }
        if (product.getCategory() == null) {
            product.setCategory(byId.getCategory());
        }
        product.setId(id);

        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public double getProductRating(Long id) {
        Product byId = findById(id);
        double reviewsCount = 0;
        double reviewsRatingSum = 0;

        for (var review : byId.getProductReviews()) {
            reviewsCount++;
            reviewsRatingSum += review.getRating();
        }

        if (reviewsCount == 0) {
            return 0;
        }

        return reviewsRatingSum / reviewsCount;
    }
}
