package com.example.nnpia_semestralka.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserDto {
    private Long id;
    private String state;
    private String email;
    private String username;
    private String password;
    private String role;
}
