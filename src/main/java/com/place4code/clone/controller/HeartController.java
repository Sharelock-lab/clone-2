package com.place4code.clone.controller;

import com.place4code.clone.service.HeartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/heart/post/{id}")
    public String updateHeart(@PathVariable final Long id) {
        heartService.updateHeathByPostId(id);
        return "redirect:/post/" + id;
    }

}
