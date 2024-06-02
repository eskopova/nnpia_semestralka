package com.example.nnpia_semestralka.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ShopDto {

    private Long id;

    private String shopName;

    private List<ProductDto> products;
}
