package com.example.nnpia_semestralka.Dto;

import com.example.nnpia_semestralka.Entity.Shop;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private Long id;

    private String productName;

    private String description;

    private String pathToImage;

    private String shopName;

    private List<Long> reviewIds;

    private String category;
}
