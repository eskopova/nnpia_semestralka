package com.example.nnpia_semestralka.Controller;

import com.example.nnpia_semestralka.Dto.AppUserDto;
import com.example.nnpia_semestralka.Entity.AppUser;
import com.example.nnpia_semestralka.Service.ConversionService;
import com.example.nnpia_semestralka.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ConversionService conversionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @GetMapping("")
    public List<AppUserDto> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return userService.findAll(pageNumber, pageSize, sortBy)
                .stream()
                .map(conversionService::toAppUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AppUserDto getUserById(@PathVariable Long id) {
        AppUser user = userService.findById(id);
        return conversionService.toAppUserDto(user);
    }

    @PostMapping("")
    public AppUserDto createUser(@RequestBody AppUserDto userDto) {
        AppUser user = conversionService.toAppUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser savedUser = userService.save(user);
        return conversionService.toAppUserDto(savedUser);
    }

    @PutMapping("/{id}")
    public AppUserDto updateUser(@PathVariable Long id, @RequestBody AppUserDto userDto) {
        AppUser user = conversionService.toAppUser(userDto);
        AppUser updatedUser = userService.update(id, user);
        return conversionService.toAppUserDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
