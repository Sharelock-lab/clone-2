package com.place4code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
public class Role {

    private Long id;

    private String name;

    private Collection<User> users;

}
