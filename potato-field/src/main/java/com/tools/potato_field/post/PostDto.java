package com.tools.potato_field.post;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private Long categoryId;

    // 기본 생성자 (No-Args Constructor)
    public PostDto() {
    }

    // 모든 필드를 받는 생성자 (All-Args Constructor)
    public PostDto(Long id, String title, String content, Long memberId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.categoryId = categoryId;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}