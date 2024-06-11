package com.example.nnpia_semestralka.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer rating;

    @Column
    private String comment;

    @Column
    private Timestamp reviewDate;

    @ManyToOne
    @JsonIgnore
    private AppUser user;

    @ManyToOne
    private Product product;
}
