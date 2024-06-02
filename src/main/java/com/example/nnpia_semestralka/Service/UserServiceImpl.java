package com.example.nnpia_semestralka.Service;

import com.example.nnpia_semestralka.Entity.AppUser;
import com.example.nnpia_semestralka.Exceptions.NotFoundException;
import com.example.nnpia_semestralka.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<AppUser> findAll(Integer page, Integer size, String sortBy) {
        PageRequest request = PageRequest.of(page, size, Sort.Direction.ASC, sortBy);
        return userRepository.findAll(request);
    }

    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Uživatel nenalezen"));
    }

    @Override
    public AppUser findByName(String name) {
        return userRepository.findAppUserByUsername(name)
                .orElseThrow(() -> new NotFoundException("Uživatel nenalezen"));
    }

    @Override
    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppUser update(Long id, AppUser user) {
        AppUser byId = findById(id);

        if (user.getUsername().isBlank()) {
            user.setUsername(byId.getUsername());
        }
        if (user.getEmail().isBlank()) {
            user.setEmail(byId.getEmail());
        }
        if (user.getPassword().isBlank()) {
            user.setPassword(byId.getPassword());
        }
        if (user.getState() == null) {
            user.setState(byId.getState());
        }

        user.setReviews(byId.getReviews());
        user.setId(id);

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isEmailAlreadyUsed(String email) {
        return userRepository.findAppUserByEmail(email).isPresent();
    }

    @Override
    public boolean isUsernameAlreadyUsed(String username) {
        return userRepository.findAppUserByUsername(username).isPresent();
    }
}
