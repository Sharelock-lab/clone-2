package com.place4code.clone.controller;

import com.place4code.clone.exception.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public String handleCustomException(final NotFoundException exception, final Model model) {
        model.addAttribute("error", exception.getMessage());
        return "error";
    }

}