package com.place4code.clone.controller;

import com.place4code.clone.model.Post;
import com.place4code.clone.service.PostService;
import com.place4code.clone.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;

    private final UserService userService;

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("posts", postService.findAllByOrderByCreatedDateDesc());
        model.addAttribute("users", userService.findAll());
        return "index";
    }

}
