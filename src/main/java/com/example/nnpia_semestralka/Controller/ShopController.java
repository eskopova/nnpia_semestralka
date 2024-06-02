package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Dto.ProductDto;
import com.example.nnpia_semestralka.Dto.ShopDto;
import com.example.nnpia_semestralka.Entity.Shop;
import com.example.nnpia_semestralka.Service.ConversionService;
import com.example.nnpia_semestralka.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ShopController {

    @Autowired
    private final ShopService shopService;

    @Autowired
    private final ConversionService conversionService;

    public ShopController(ShopService shopService, ConversionService conversionService) {
        this.shopService = shopService;
        this.conversionService = conversionService;
    }

    @GetMapping("/shops")
    public List<String> getAllShopNames() {
        return shopService.getAllShopNames();
    }

    @GetMapping("/shops-all")
    public List<ShopDto> getAllShopsSimplified() {
        return shopService.getAllShops()
                .stream()
                .map(conversionService::toShopDto)
                .toList();
    }

    @GetMapping("/shops/{name}")
    public List<ProductDto> getAllShopProducts(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return shopService.getProducts(name, pageNumber, pageSize, sortBy)
                .stream()
                .map(conversionService::toProductDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/shops")
    public ShopDto createShop(@RequestBody ShopDto shopDto) {
        Shop shop = conversionService.toShop(shopDto);
        return conversionService.toShopDto(shopService.save(shop));
    }

    @DeleteMapping("/shops/{id}")
    public void deleteShop(@PathVariable Long id) {
        shopService.delete(id);
    }
}
