package com.place4code.clone.repository;

import com.place4code.clone.model.Comment;
import com.place4code.clone.model.Post;
import com.place4code.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostOrderByCreatedDateDesc(Post post);

    Optional<Comment> findByIdAndUser(Long commentId, User loggedInUser);
}
