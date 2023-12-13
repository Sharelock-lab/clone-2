package com.place4code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class Comment {

    private Long id;

    private String text;

    private User user;

    private Post post;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

}
