package com.tools.potato_field.postimage;

import jakarta.persistence.Entity;

@Entity
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // Getters and Setters
}
