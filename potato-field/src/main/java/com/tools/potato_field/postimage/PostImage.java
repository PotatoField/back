package com.tools.potato_field.postimage;

import com.tools.potato_field.post.Post;
import jakarta.persistence.*;

@Entity
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
