package com.place4code.clone.service;

import com.place4code.clone.exception.NotFoundException;
import com.place4code.clone.model.User;
import com.place4code.clone.repository.RoleRepository;
import com.place4code.clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MailService mailService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findLoggedInUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new NotFoundException("Użytkownik nie istnieje."));

    }

    public User register(User user) {
        final User savedUser = userRepository.save(
                User.builder()
                        .enabled(false)
                        .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                        .roles(Collections.singleton(roleRepository.findByName("ROLE_USER").get()))
                        .email(user.getEmail())
                        .name(user.getName())
                        .country(user.getCountry())
                        .description(user.getDescription())
                        .build()
        );
        mailService.sendActivationEmail(savedUser);
        return savedUser;
    }

    public void checkUniqueness(User user, BindingResult bindingResult) {
        if (userRepository.existsByEmail(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "email", "Taki e-mail już istnieje."));
        }
        if (userRepository.existsByName(user.getName())) {
            bindingResult.addError(new FieldError("user", "name", "Taka nazwa już istnieje."));
        }
    }
}
