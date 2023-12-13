package com.place4code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
public class User {

    private Long id;

    private String email;

    private String name;

    private String password;

    private String country;

    private String description;

    private String activationToken;

    private String resetPasswordToken;

    private boolean enabled;

    private LocalDateTime createdDate;

    private Collection<Role> roles;


}
