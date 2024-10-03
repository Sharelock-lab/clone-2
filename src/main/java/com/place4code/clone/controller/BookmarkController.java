package com.place4code.clone.controller;

import com.place4code.clone.service.BookmarkService;
import com.place4code.clone.service.PostService;
import com.place4code.clone.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final UserService userService;
    private final PostService postService;

    @PostMapping("/bookmark/post/{id}")
    public String updateBookmark(@PathVariable final Long id) {
        bookmarkService.updateBookmarkByPostId(id);
        return "redirect:/post/" + id;
    }

    @GetMapping("/bookmarks")
    public String bookmarks(final Model model) {
        model.addAttribute("posts", postService.findAllByUserAndBookmark());
        model.addAttribute("users", userService.findAll());
        return "bookmarks";
    }

}
