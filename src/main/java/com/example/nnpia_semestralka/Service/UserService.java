package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.AppUser;
import org.springframework.data.domain.Page;



public interface UserService {

    Page<AppUser> findAll(Integer page, Integer size, String sortBy);

    AppUser findById(Long id);

    AppUser findByName(String name);

    AppUser save(AppUser user);

    AppUser update(Long id, AppUser user);

    void delete(Long id);

    boolean isEmailAlreadyUsed(String email);

    boolean isUsernameAlreadyUsed(String username);
}
