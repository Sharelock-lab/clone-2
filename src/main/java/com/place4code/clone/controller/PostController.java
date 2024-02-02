package com.place4code.clone.controller;

import com.place4code.clone.model.Comment;
import com.place4code.clone.model.Post;
import com.place4code.clone.service.CommentService;
import com.place4code.clone.service.PostService;
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
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping("/createPost")
    public String createPost(@Valid Post post,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            model.addAttribute("posts", postService.findAllByOrderByCreatedDateDesc());
            model.addAttribute("users", userService.findAll());
            return "index";
        } else {
            postService.saveNewPost(post);
            redirectAttributes.addFlashAttribute("post_added", true);
            return "redirect:/";
        }

    }

    @GetMapping("/post/{id}")
    public String postView(final Model model, @PathVariable final Long id) {
        final Post post = postService.findPostByIdAndIncrementViews(id);
        model.addAttribute("post", post);
        model.addAttribute("isLiked", postService.isThePostLikedByTheUser(post));
        model.addAttribute("isMarked", postService.isThePostMarkedByTheUser(post));
        model.addAttribute("comments", commentService.findAllByPostOrderByCreatedDateDesc(post));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("comment", new Comment());
        return "post";
    }

    @PostMapping("/deletePost/{postId}")
    public String deleteComment(final Model model,
                                final RedirectAttributes redirectAttributes,
                                final @PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        redirectAttributes.addFlashAttribute("post_deleted", true);
        return "redirect:/";
    }

}
