package com.place4code.clone.service;

import com.place4code.clone.exception.NotFoundException;
import com.place4code.clone.model.User;
import com.place4code.clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findLoggedInUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new NotFoundException("Użytkownik nie istnieje."));

    }

}
