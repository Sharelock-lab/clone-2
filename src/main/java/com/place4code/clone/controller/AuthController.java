package com.place4code.clone.controller;

import com.place4code.clone.model.User;
import com.place4code.clone.service.UserService;
import com.place4code.model.Message;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/activate/{email}/{activationToken}")
    public String activate(final @PathVariable String email,
                           final @PathVariable String activationToken,
                           final RedirectAttributes redirectAttributes) {
        userService.activateUser(email, activationToken);
        redirectAttributes.addFlashAttribute("activated", true);
        return "redirect:/login";
    }


    @GetMapping("/resetPassword")
    public String resetPasswordEmailForm() {
        return "auth/reset_email_form";
    }

    @PostMapping("/resetPassword")
    public String resetPasswordRequest(final RedirectAttributes redirectAttributes,
                                       final @RequestParam("email") String email) {
        if (userService.sendResetPasswordEMail(email)) {
            redirectAttributes.addFlashAttribute(
                    "message", new Message("Wysłaliśmy Ci wiadomość e-mail do resetu twojego hasła.", "success"));
        } else {
            redirectAttributes.addFlashAttribute(
                    "message", new Message("Podany adres e-mail nie istnieje, albo nie aktywowałaś/eś jeszcze swojego konta.", "error"));
        }
        redirectAttributes.addFlashAttribute("response", true);
        return "redirect:/resetPassword";
    }

    @GetMapping("resetPassword/{token}")
    public String showNewPasswordForm(final @PathVariable String token,
                                      final Model model) {
        model.addAttribute("user", userService.findByResetPasswordTokenAndClearPassword(token));
        return "auth/new_password_form";
    }

    @PostMapping("/setNewPassword")
    public String setNewPassword(final @Valid User user,
                                 final BindingResult bindingResult,
                                 final Model model,
                                 final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "auth/new_password_form";
        } else {
            userService.updatePassword(user);
            redirectAttributes.addFlashAttribute("password_changed", true);
            return "redirect:/login";
        }
    }



}
