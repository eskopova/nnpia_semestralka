package com.example.nnpia_semestralka.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddOrEditProductDto {

    private Long id;

    private String productName;

    private String description;

    private MultipartFile image;
}
