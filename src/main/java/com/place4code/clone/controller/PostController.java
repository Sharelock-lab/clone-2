package com.place4code.clone.controller;

import com.place4code.clone.model.Post;
import com.place4code.clone.service.PostService;
import com.place4code.clone.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping("/createPost")
    public String createPost(@Valid Post post,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            model.addAttribute("posts", postService.findAllByOrderByCreatedDateDesc());
            model.addAttribute("users", userService.findAll());
            return "index";
        } else {
            postService.saveNewPost(post);
            return "redirect:/";
        }

    }

}
