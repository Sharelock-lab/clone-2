package com.place4code.clone.repository;

import com.place4code.clone.model.Heart;
import com.place4code.clone.model.Post;
import com.place4code.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndPost(User loggedInUser, Post post);

}
