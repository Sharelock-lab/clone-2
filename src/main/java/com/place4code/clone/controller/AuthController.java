package com.place4code.clone.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AuthController {

    @GetMapping("/login")
    String login() {
        return "auth/login";
    }

}
