package com.example.nnpia_semestralka.Repository;

import com.example.nnpia_semestralka.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
