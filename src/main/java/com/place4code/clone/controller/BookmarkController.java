package com.place4code.clone.controller;

import com.place4code.clone.service.BookmarkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark/post/{id}")
    public String updateBookmark(@PathVariable final Long id) {
        bookmarkService.updateBookmarkByPostId(id);
        return "redirect:/post/" + id;
    }

}
