package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.AccountState;
import com.example.nnpia_semestralka.Entity.AppUser;
import com.example.nnpia_semestralka.Repository.UserRepository;
import com.example.nnpia_semestralka.Security.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    private static final AppUser user = new AppUser(100L, AccountState.NEW, "mail@em.cz", "name", "password", "USER", Collections.emptyList());

    private AppUser savedUser = null;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        savedUser = userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    /**
     * Pokud by tento test měl testovat chybový stav (vyhození výjimky),
     * označili bychom ho anotací @Test(expected = SomeException.class).
     */
    @Test
    void testFindById() {
        var byId = userService.findById(savedUser.getId());

        assertEquals(savedUser, byId);
    }
}
