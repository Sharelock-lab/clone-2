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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class CommentController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping("/createComment")
    public String createComment(final @Valid Comment comment,
                                final BindingResult bindingResult,
                                final @ModelAttribute("postId") Long postId,
                                final Model model,
                                final RedirectAttributes redirectAttributes) {
        final Post post = postService.findPostById(postId);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            model.addAttribute("isLiked", postService.isThePostLikedByTheUser(post));
            model.addAttribute("isMarked", postService.isThePostMarkedByTheUser(post));
            model.addAttribute("comments", commentService.findAllByPostOrderByCreatedDateDesc(post));
            model.addAttribute("users", userService.findAll());
            model.addAttribute("comment", comment);
            return "post";
        } else {
            commentService.saveNewCommentForPost(comment, post);
            redirectAttributes.addFlashAttribute("comment_added", true);
            return "redirect:/post/" + postId;
        }
    }

    @PostMapping("/deleteComment/{postId}/{commentId}")
    public String deleteComment(final Model model,
                                final RedirectAttributes redirectAttributes,
                                final @PathVariable("postId") Long postId,
                                final @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        redirectAttributes.addFlashAttribute("comment_deleted", true);
        return "redirect:/post/" + postId;
    }

}
