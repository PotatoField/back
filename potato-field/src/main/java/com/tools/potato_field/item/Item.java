package com.tools.potato_field.item;

import com.tools.potato_field.member.Member;

import com.tools.potato_field.post.Post;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String itemURL;
    private Integer iconNumber;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // Getters and Setters
}