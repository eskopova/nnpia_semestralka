package com.example.nnpia_semestralka.Dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String productName;

    private String description;

    //private MultipartFile image;
    private String pathToImage;

    private String shop;
}
