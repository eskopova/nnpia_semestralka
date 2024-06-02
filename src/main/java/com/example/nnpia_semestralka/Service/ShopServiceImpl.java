package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.Shop;
import com.example.nnpia_semestralka.Exceptions.NotFoundException;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import com.example.nnpia_semestralka.Repository.ShopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;

    public ShopServiceImpl(ShopRepository shopRepository, ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<String> getAllShopNames() {
        return shopRepository.findAll()
                .stream()
                .map(Shop::getShopName)
                .collect(Collectors.toList());
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findById(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Obchod nenalezen"));
    }

    @Override
    public Shop findByName(String name) {
        return shopRepository.findShopByShopName(name)
                .orElseThrow(() -> new NotFoundException("Obchod nenalezen"));
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop update(Long id, Shop shop) {
        Shop byId = findById(id);

        if (shop.getShopName().isBlank()) {
            shop.setShopName(byId.getShopName());
        }

        shop.setId(id);

        return shopRepository.save(shop);
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }

    public Page<Product> getProducts(String name, Integer page, Integer size, String sortBy) {
        var pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Shop shop = shopRepository.findShopByShopName(name)
                .orElseThrow(() -> new NotFoundException("Obchod nenalezen"));
        return productRepository.findByShop(shop, pageable);
    }
}