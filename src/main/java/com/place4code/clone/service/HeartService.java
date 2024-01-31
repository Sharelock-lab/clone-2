package com.place4code.clone.service;

import com.place4code.clone.model.Heart;
import com.place4code.clone.model.Post;
import com.place4code.clone.model.User;
import com.place4code.clone.repository.HeartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class HeartService {

    private final UserService userService;

    private final PostService postService;

    private final HeartRepository heartRepository;

    public void updateHeathByPostId(final Long id) {
        final User user = userService.findLoggedInUser();
        final Post post = postService.findPostById(id);
        final Optional<Heart> heartOptional = heartRepository.findByUserAndPost(user, post);

        if (heartOptional.isPresent()) {
            heartRepository.delete(heartOptional.get());
        } else {
            heartRepository.save(Heart.builder().user(user).post(post).build());
        }

    }
}
