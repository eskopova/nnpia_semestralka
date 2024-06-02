package com.example.nnpia_semestralka.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountState state;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String username;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @NotNull
    @JsonIgnore
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews = Collections.emptyList();
}