package com.place4code.clone.controller;

import com.place4code.clone.model.Post;
import com.place4code.clone.model.User;
import com.place4code.clone.service.PostService;
import com.place4code.clone.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class ProfileController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("/profile/{id}")
    public String index(final Model model, @PathVariable Long id) {
        final User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAllByUserOrderByCreatedDateDesc(user));
        model.addAttribute("users", userService.findAll());
        return "profile";
    }

}
