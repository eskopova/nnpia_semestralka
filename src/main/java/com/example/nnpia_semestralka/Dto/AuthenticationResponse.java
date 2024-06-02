package com.example.nnpia_semestralka.Dto;

import com.example.nnpia_semestralka.Entity.AppUser;
import lombok.Data;

@Data
public class AuthenticationResponse {
    String token;
    AppUser user;
}
