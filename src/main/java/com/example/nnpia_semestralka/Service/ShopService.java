package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.Shop;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShopService {

    List<String> getAllShopNames();

    List<Shop> getAllShops();

    Page<Product> getProducts(String name, Integer page, Integer size, String sortBy);

    Shop findById(Long id);

    Shop findByName(String name);

    Shop save(Shop shop);

    Shop update(Long id, Shop shop);

    void delete(Long id);
}
