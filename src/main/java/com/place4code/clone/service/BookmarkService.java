package com.place4code.clone.service;

import com.place4code.clone.model.Bookmark;
import com.place4code.clone.model.Post;
import com.place4code.clone.model.User;
import com.place4code.clone.repository.BookmarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BookmarkService {

    private final UserService userService;

    private final PostService postService;

    private final BookmarkRepository bookmarkRepository;

    public void updateBookmarkByPostId(final Long id) {
        final User user = userService.findLoggedInUser();
        final Post post = postService.findPostById(id);
        final Optional<Bookmark> heartOptional = bookmarkRepository.findByUserAndPost(user, post);

        if (heartOptional.isPresent()) {
            bookmarkRepository.delete(heartOptional.get());
        } else {
            bookmarkRepository.save(Bookmark.builder().user(user).post(post).build());
        }

    }
}
