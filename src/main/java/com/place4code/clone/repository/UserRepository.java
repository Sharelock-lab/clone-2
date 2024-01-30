package com.place4code.clone.repository;

import com.place4code.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    Optional<User> findByEmailAndActivationToken(String email, String activationToken);
}
