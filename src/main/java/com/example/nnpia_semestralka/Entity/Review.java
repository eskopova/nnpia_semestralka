package com.example.nnpia_semestralka.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne
    private User user;

    @ManyToOne
    private Product product;*/

    @Column
    private Integer rating;

    @Column
    private String comment;

    @Column
    private Timestamp reviewDate;
}
