package com.example.nnpia_semestralka.Dto;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReviewDto {

    private Long id;

    private Integer rating;

    private String comment;

    private Timestamp reviewDate;

    private Long userId;

    private Long productId;
}
