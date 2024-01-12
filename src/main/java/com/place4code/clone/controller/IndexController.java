package com.place4code.clone.controller;

import com.place4code.clone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("posts", postService.findAllByOrderByCreatedDateDesc());
        return "index";
    }

}
