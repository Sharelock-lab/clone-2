package com.place4code.clone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Integer views = 0;

    @ManyToOne
    private User user;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "post")
    private List<Heart> hearts;

    @OneToMany(mappedBy = "post")
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


}
