package com.tools.potato_field.post;

import com.tools.potato_field.category.Category_1;
import jakarta.persistence.*;
import java.util.List;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.postimage.PostImage;

@Entity
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
    private Category_1 category;

    // 기본 생성자
    public Post() {
    }

    // 모든 필드를 받는 생성자
    public Post(String title, String content, Member member, Category_1 category) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Category_1 getCategory() {
        return category;
    }

    public void setCategory(Category_1 category) {
        this.category = category;
    }
}
