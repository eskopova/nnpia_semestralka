package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Product;

import java.util.Map;

public interface ShoppingCartService {

    void add(Long id);

    void remove(Long id);

    Map<Product, Integer> getCart();

    void checkout();
}
