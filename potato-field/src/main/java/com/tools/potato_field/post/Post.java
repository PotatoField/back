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
    @JoinColumn(name = "id2")
    private Category_1 category;  // 성별 카테고리와의 관계

    @ManyToOne
    @JoinColumn(name = "id3")
    private Member member;  // 회원과의 관계

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> postImages;  // 게시물 이미지와의 관계

    // 기본 생성자
    public Post() {

    }

    // 모든 필드를 포함하는 생성자
    public Post(String title, String content, Category_1 category, Member member) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.member = member;
    }

    // Getter 및 Setter 메서드

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

    public Category_1 getCategory() {
        return category;
    }

    public void setCategory(Category_1 category) {
        this.category = category;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }

    // 편의 메서드 (양방향 관계에서의 데이터 처리 편의성)
    public void addPostImage(PostImage postImage) {
        postImages.add(postImage);
        postImage.setPost(this);
    }

    public void removePostImage(PostImage postImage) {
        postImages.remove(postImage);
        postImage.setPost(null);
    }
}
