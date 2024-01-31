package com.place4code.clone.repository;

import com.place4code.clone.model.Comment;
import com.place4code.clone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostOrderByCreatedDateDesc(Post post);

}
