package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.Product;
import com.example.nnpia_semestralka.Entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findShopByShopName(String shopName);

}
