package com.place4code.clone.service;

import com.place4code.clone.model.Comment;
import com.place4code.clone.model.Post;
import com.place4code.clone.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    CommentRepository commentRepository;

    public List<Comment> findAllByPostOrderByCreatedDateDesc(final Post post) {
        return commentRepository.findAllByPostOrderByCreatedDateDesc(post);
    }
}
