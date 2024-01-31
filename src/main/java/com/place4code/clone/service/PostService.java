package com.place4code.clone.service;

import com.place4code.clone.exception.NotFoundException;
import com.place4code.clone.model.Post;
import com.place4code.clone.repository.BookmarkRepository;
import com.place4code.clone.repository.HeartRepository;
import com.place4code.clone.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final HeartRepository heartRepository;
    private final BookmarkRepository bookmarkRepository;

    private final UserService userService;


    public List<Post> findAllByOrderByCreatedDateDesc() {
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    public void saveNewPost(Post post) {
        post.setUser(userService.findLoggedInUser());
        postRepository.save(post);
    }

    public boolean isThePostLikedByTheUser(final Post post) {
        return heartRepository.findByUserAndPost(userService.findLoggedInUser(), post).isPresent();
    }

    public boolean isThePostMarkedByTheUser(final Post post) {
        return bookmarkRepository.findByUserAndPost(userService.findLoggedInUser(), post).isPresent();
    }

    public Post findPostByIdAndIncrementViews(final Long id) {
        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Taki post nie istnieje."));
        return postRepository.save(post.incrementViews());
    }
}
