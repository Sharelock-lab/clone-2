package com.place4code.clone.service;

import com.place4code.clone.model.Post;
import com.place4code.clone.repository.PostRepository;
import com.place4code.clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public List<Post> findAllByOrderByCreatedDateDesc() {
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    public void saveNewPost(Post post) {
        post.setUser(userRepository.findAll().stream().findFirst().get());
        postRepository.save(post);
    }

}
