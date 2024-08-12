package com.tools.potato_field.postimage;

public class PostImage {
    private Long id;
    private String imageUrl;
    private Long postId;

    public PostImage() {
    }

    public PostImage(Long id, String imageUrl, Long postId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getPostId() {
        return postId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
