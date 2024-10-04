package com.place4code.clone.service;

import com.place4code.clone.exception.NotFoundException;
import com.place4code.clone.model.User;
import com.place4code.clone.repository.RoleRepository;
import com.place4code.clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    public User register(final User user) {
        final User savedUser = userRepository.save(
                User.builder()
                        .enabled(false)
                        .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                        .roles(Collections.singleton(roleRepository.findByName("ROLE_USER").get()))
                        .email(user.getEmail())
                        .name(user.getName())
                        .country(user.getCountry())
                        .description(user.getDescription())
                        .activationToken(UUID.randomUUID().toString())
                        .build()
        );
        mailService.sendActivationEmail(savedUser);
        return savedUser;
    }

    public void checkUniqueness(final User user, final BindingResult bindingResult) {
        if (userRepository.existsByEmail(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "email", "Taki e-mail już istnieje."));
        }
        if (userRepository.existsByName(user.getName())) {
            bindingResult.addError(new FieldError("user", "name", "Taka nazwa już istnieje."));
        }
    }

    public void activateUser(final String email, final String activationToken) {
        SecurityContextHolder.clearContext();
        final User user = userRepository.findByEmailAndActivationToken(email, activationToken)
                .orElseThrow(() -> new NotFoundException("Użytkownik nie istnieje, albo jest już aktywny."));
        user.setEnabled(true);
        user.setActivationToken(null);
        userRepository.save(user);
    }

    public boolean sendResetPasswordEMail(final String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    user.setResetPasswordToken(UUID.randomUUID().toString());
                    mailService.sendResetPasswordEMail(user);
                    userRepository.save(user);
                    return true;
                }).orElse(false);
    }

    public User findByResetPasswordTokenAndClearPassword(final String token) {
        final User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new NotFoundException("Użytkownik nie istnieje."));
        user.setPassword(null);
        return user;
    }

    @Transactional
    public void updatePassword(User user) {
        userRepository.updatePassword(user.getEmail(), new BCryptPasswordEncoder().encode(user.getPassword()));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Użytkownik nie istnieje."));
    }
}
