package com.place4code.clone.controller;

import com.place4code.clone.model.User;
import com.place4code.clone.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    String register(final Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    String register(final @Valid User user,
                    final BindingResult bindingResult,
                    final Model model,
                    final RedirectAttributes redirectAttributes) {
        userService.checkUniqueness(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "auth/register";
        } else {
            userService.register(user);
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/register";
        }
    }

}
