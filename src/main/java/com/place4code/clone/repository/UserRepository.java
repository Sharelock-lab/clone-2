package com.place4code.clone.repository;

import com.place4code.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    Optional<User> findByEmailAndActivationToken(String email, String activationToken);

    Optional<User> findByResetPasswordToken(String token);

    @Modifying
    @Query("UPDATE User u SET u.password = :password, u.resetPasswordToken = null WHERE u.email = :email")
    void updatePassword(@Param("email") String email, @Param("password") String password);

}
