package com.place4code.clone.controller;

import com.place4code.clone.model.Post;
import com.place4code.clone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @PostMapping("/createPost")
    public String createPost(Post post) {
        postService.saveNewPost(post);
        return "redirect:/";

    }

}
