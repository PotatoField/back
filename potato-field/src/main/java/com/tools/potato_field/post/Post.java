package com.tools.potato_field.post;

import com.tools.potato_field.category.Category;
import jakarta.persistence.*;
import com.tools.potato_field.member.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // 기본 생성자
    public Post() {
    }
    
    // 모든 필드를 받는 생성자
    public Post(String title, String content, Member member, Category category) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.category = category;
    }

    // Getters and Setters
}
