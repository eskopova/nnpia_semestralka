package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.NotFoundException;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produkt nenalezen"));
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
        if (product.getShop().isBlank()) {
            product.setShop(byId.getShop());
        }
        product.setId(id);

        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
