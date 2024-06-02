package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByUsername(String userName);

    Optional<AppUser> findAppUserByEmail(String email);

}
