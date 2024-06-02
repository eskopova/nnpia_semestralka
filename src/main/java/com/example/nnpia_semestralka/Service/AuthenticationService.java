package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Dto.AppUserDto;
import com.example.nnpia_semestralka.Entity.AccountState;
import com.example.nnpia_semestralka.Entity.AppUser;
import com.example.nnpia_semestralka.Exceptions.InvalidNewUserException;
import com.example.nnpia_semestralka.Exceptions.NotFoundException;
import com.example.nnpia_semestralka.Dto.AuthenticationResponse;
import com.example.nnpia_semestralka.Dto.LoginRequest;
import com.example.nnpia_semestralka.Security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final ConversionService conversionService;

    public AuthenticationResponse login(LoginRequest loginRequest) {
        boolean isUserExisting = userService.isUsernameAlreadyUsed(loginRequest.getUsername());
        if (isUserExisting) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            AppUser user = userService.findByName(loginRequest.getUsername());
            final String jwt = jwtUtil.generateToken(loginRequest.getUsername());

            AuthenticationResponse response = new AuthenticationResponse();
            response.setToken(jwt);
            response.setUser(user);

            return response;
        } else {
            throw new NotFoundException("Username not found");
        }
    }

    public AuthenticationResponse register(AppUserDto userDto) {

        if (userService.isEmailAlreadyUsed(userDto.getEmail())) {
            throw new InvalidNewUserException("Email is already in use");
        }
        if (userService.isUsernameAlreadyUsed(userDto.getUsername())) {
            throw new InvalidNewUserException("Username is already in use");
        }
        if (userDto.getPassword().isBlank() || userDto.getPassword().length() < 6) {
            throw new InvalidNewUserException("Password is invalid");
        }

        userDto.setRole("USER");
        userDto.setState(AccountState.NEW.name());
        AppUser user = conversionService.toAppUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(user);
        if (!userService.isUsernameAlreadyUsed(user.getUsername()))
        {
            throw new InvalidNewUserException("User could not be registered");
        }

        final String jwt = jwtUtil.generateToken(user.getUsername());

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwt);
        response.setUser(user);
        return response;
    }
}
