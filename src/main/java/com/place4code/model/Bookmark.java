package com.place4code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Bookmark {

    private Long id;

    private Post post;

    private User user;

}
