package com.place4code.clone.repository;

import com.place4code.clone.model.Bookmark;
import com.place4code.clone.model.Post;
import com.place4code.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUserAndPost(User loggedInUser, Post post);

}
