package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Map<Product, Integer> cart;

    private final ProductRepository productRepository;

    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.cart = new HashMap<>();
    }

    @Override
    public void add(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (cart.containsKey(product)) {
            cart.replace(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    @Override
    public void remove(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (cart.containsKey(product)) {
            if (cart.get(product) > 1) {
                cart.replace(product, cart.get(product) - 1);
            } else {
                cart.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }
}
