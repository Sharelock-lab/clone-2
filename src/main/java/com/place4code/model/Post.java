package com.place4code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Post {

    private Long id;

    private String text;

    private Integer views = 0;

    private User user;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private List<Heart> hearts;

    private List<Bookmark> bookmarks;

    private List<Comment> comments;



}
