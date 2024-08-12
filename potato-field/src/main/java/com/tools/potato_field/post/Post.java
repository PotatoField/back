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


}

