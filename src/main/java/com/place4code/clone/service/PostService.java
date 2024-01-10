package com.place4code.clone.service;

import com.place4code.clone.model.Post;
import com.place4code.clone.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

}
