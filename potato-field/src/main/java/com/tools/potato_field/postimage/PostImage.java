package com.tools.potato_field.postimage;

import com.tools.potato_field.post.Post;
import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "id")
    private Post post;  // 게시물과의 관계

    // 기본 생성자
    public PostImage() {}

    // 모든 필드를 포함하는 생성자
    public PostImage(String imageUrl, Post post) {
        this.imageUrl = imageUrl;
        this.post = post;
    }

    // Getter 및 Setter 메서드

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
