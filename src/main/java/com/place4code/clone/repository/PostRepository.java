package com.place4code.clone.repository;

import com.place4code.clone.model.Post;
import com.place4code.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedDateDesc();

    Optional<Post> findByIdAndUser(Long postId, User loggedInUser);
}
