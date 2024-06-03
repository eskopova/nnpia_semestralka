package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Dto.AppUserDto;
import com.example.nnpia_semestralka.Dto.ProductDto;
import com.example.nnpia_semestralka.Dto.ReviewDto;
import com.example.nnpia_semestralka.Dto.ShopDto;
import com.example.nnpia_semestralka.Entity.*;
import com.example.nnpia_semestralka.Exceptions.NotFoundException;
import com.example.nnpia_semestralka.Repository.ProductRepository;
import com.example.nnpia_semestralka.Repository.ReviewRepository;
import com.example.nnpia_semestralka.Repository.ShopRepository;
import com.example.nnpia_semestralka.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ConversionService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public AppUserDto toAppUserDto(AppUser user) {
        AppUserDto userDto = new AppUserDto();
        userDto.setId(user.getId());
        userDto.setState(user.getState().toString());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public AppUser toAppUser(AppUserDto userDto) {
        AppUser user = new AppUser();
        user.setId(userDto.getId());
        user.setState(AccountState.valueOf(userDto.getState()));
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    public ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        ProductCategory category = product.getCategory() == null ? ProductCategory.Obyčejné : product.getCategory();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setPathToImage(product.getPathToImage());
        productDto.setDescription(product.getDescription());
        productDto.setShopName(product.getShop().getShopName());
        productDto.setCategory(category.name());
        productDto.setReviewIds(product.getProductReviews()
                .stream()
                .map(Review::getId)
                .toList());

        return productDto;
    }

    public Product toProduct(ProductDto productDto) {
        Product product = new Product();

        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setPathToImage(productDto.getPathToImage());
        product.setDescription(productDto.getDescription());
        product.setShop(shopRepository.findShopByShopName(productDto.getShopName())
                .orElseThrow(() -> new NotFoundException("Obchod nenalezen")));
        product.setCategory(toCategory(productDto.getCategory()));

        if (productDto.getReviewIds() != null) {
            product.setProductReviews(
                    productDto.getReviewIds()
                            .stream()
                            .map(id -> reviewRepository.findById(id)
                                    .orElseThrow(() -> new NotFoundException("Recenze nenalezena")))
                            .collect(Collectors.toSet()));
        }

        return product;
    }

    public ShopDto toShopDto(Shop shop) {
        ShopDto shopDto = new ShopDto();

        shopDto.setId(shop.getId());
        shopDto.setShopName(shop.getShopName());
        shopDto.setProducts(shop.getProducts()
                .stream()
                .map(this::toProductDto)
                .toList());
        return shopDto;
    }

    public Shop toShop(ShopDto shopDto) {
        Shop shop = new Shop();

        shop.setId(shopDto.getId());
        shop.setShopName(shopDto.getShopName());
        if (shopDto.getProducts() != null) {
            shop.setProducts(shopDto.getProducts()
                    .stream()
                    .map(this::toProduct)
                    .toList());
        }
        return shop;
    }

    public Review toReview(ReviewDto reviewDto) {
        Review review = new Review();

        review.setId(reviewDto.getId());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setReviewDate(reviewDto.getReviewDate());
        review.setUser(userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new NotFoundException("Uživatel nenalezen")));
        review.setProduct(productRepository.findById(reviewDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Produkt nenalezen")));

        return review;
    }

    public ReviewDto toReviewDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setReviewDate(review.getReviewDate());
        dto.setUserId(review.getUser().getId());
        dto.setProductId(review.getProduct().getId());
        return dto;
    }

    private ProductCategory toCategory(String categoryName) {
        if (categoryName.equals(ProductCategory.Slané.name())) {
            return ProductCategory.Slané;
        }
        if (categoryName.equals(ProductCategory.Sladké.name())) {
            return ProductCategory.Sladké;
        }
        if (categoryName.equals(ProductCategory.Jiné.name())) {
            return ProductCategory.Jiné;
        }

        return ProductCategory.Obyčejné;
    }
}
