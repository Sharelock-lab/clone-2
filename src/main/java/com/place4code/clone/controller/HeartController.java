package com.place4code.clone.controller;

import com.place4code.clone.model.Post;
import com.place4code.clone.service.HeartService;
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
public class HeartController {

    private final HeartService heartService;
    private final UserService userService;
    private final PostService postService;

    @PostMapping("/heart/post/{id}")
    public String updateHeart(@PathVariable final Long id) {
        heartService.updateHeathByPostId(id);
        return "redirect:/post/" + id;
    }

    @GetMapping("/hearts")
    public String hearts(final Model model) {
        model.addAttribute("posts", postService.findAllByUserAndHearts());
        model.addAttribute("users", userService.findAll());
        return "hearts";
    }

}
