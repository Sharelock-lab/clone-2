package com.place4code.clone.service;

import com.place4code.clone.exception.NotFoundException;
import com.place4code.clone.model.Post;
import com.place4code.clone.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserService userService;

    public List<Post> findAllByOrderByCreatedDateDesc() {
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    public void saveNewPost(Post post) {
        post.setUser(userService.findLoggedInUser());
        postRepository.save(post);
    }

    public Post findById(final Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Taki post nie istnieje."));
    }
}
