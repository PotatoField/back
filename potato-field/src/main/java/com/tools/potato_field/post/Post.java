package com.tools.potato_field.post;

import com.tools.potato_field.PostCategory;
import com.tools.potato_field.category.Category_1;
import com.tools.potato_field.like.Like;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.postimage.PostImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Category_1 gender;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member author;

    @OneToMany(mappedBy = "post")
    private Set<PostImage> images;

    @OneToMany(mappedBy = "post")
    private Set<PostCategory> categories;
}



