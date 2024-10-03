package com.place4code.clone.service;

import com.place4code.clone.exception.NotFoundException;
import com.place4code.clone.model.Bookmark;
import com.place4code.clone.model.Heart;
import com.place4code.clone.model.Post;
import com.place4code.clone.repository.BookmarkRepository;
import com.place4code.clone.repository.HeartRepository;
import com.place4code.clone.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        final Post post = findPostById(id);
        return postRepository.save(post.incrementViews());
    }

    public Post findPostById(final Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Taki post nie istnieje."));
    }

    public void deletePost(final Long postId) {
        final Post post = postRepository.findByIdAndUser(postId, userService.findLoggedInUser())
                .orElseThrow(() -> new NotFoundException("Ten użytkownik nie ma takiego postu"));
        postRepository.delete(post);
    }

    public Post findPostByIdAndLoggedInUser(final Long postId) {
        return postRepository.findByIdAndUser(postId, userService.findLoggedInUser())
                .orElseThrow(() -> new NotFoundException("Ten użytkownik nie ma takiego postu"));
    }

    @Transactional
    public void updatePost(final @NonNull Post post) {
        final Post postFromDB = findPostByIdAndLoggedInUser(post.getId());
        postFromDB.setText(post.getText());
        postRepository.save(postFromDB);
    }

    public List<Post> findAllByUserAndHearts() {
        final List<Heart> hearts = heartRepository.findAllByUser(userService.findLoggedInUser());
        return postRepository.findAllByHeartsInOrderByCreatedDateDesc(hearts);
    }

    public List<Post> findAllByUserAndBookmark() {
        final List<Bookmark> bookmarks = bookmarkRepository.findAllByUser(userService.findLoggedInUser());
        return postRepository.findAllByBookmarksInOrderByCreatedDateDesc(bookmarks);
    }
}
