package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Dto.ProductDto;
import com.example.nnpia_semestralka.Entity.Product;

public class ConversionService {

    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setPathToImage(product.getPathToImage());
        productDto.setDescription(product.getDescription());
        productDto.setShop(product.getShop());

        return productDto;
    }

    public static Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setPathToImage(productDto.getPathToImage());
        product.setDescription(productDto.getDescription());
        product.setShop(productDto.getShop());

        return product;
    }
}
