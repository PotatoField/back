package com.tools.potato_field.post;


import com.tools.potato_field.member.Member;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Category1 gender;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member author;

    @OneToMany(mappedBy = "post")
    private Set<PostImage> images;

    @OneToMany(mappedBy = "post")
    private Set<PostCategory> categories;

    // Getters and Setters
}

