package com.place4code.clone.controller;

import com.place4code.clone.model.Post;
import com.place4code.clone.model.User;
import com.place4code.clone.service.PostService;
import com.place4code.clone.service.ProfileService;
import com.place4code.clone.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class ProfileController {

    private final PostService postService;
    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/profile/{id}")
    public String index(final Model model, @PathVariable Long id) {
        final User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAllByUserOrderByCreatedDateDesc(user));
        model.addAttribute("users", userService.findAll());
        return "profile";
    }

    @GetMapping("/profile/edit/{id}")
    public String profileEdit(final Model model, @PathVariable Long id) {
        final User user = profileService.findUserByIdAndCheckAuthentication(id);
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAll());
        return "profile-edit";
    }

    @PostMapping("/updateProfile")
    String updateProfile(final @Valid User user,
                         final BindingResult bindingResult,
                         final Model model,
                         final RedirectAttributes redirectAttributes) {
        userService.checkUniquenessByEdit(user, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "profile-edit";
        } else {
            final User userFromDB = userService.updateUser(user);
            redirectAttributes.addFlashAttribute("user_updated", true);
            return "redirect:/profile/" + userFromDB.getId();
        }
    }

}
