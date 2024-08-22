package com.tools.potato_field.post;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String member;

    // 기본 생성자
    public PostDto() {}

    // 매개변수를 받는 생성자
    public PostDto(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
    }

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

    public String getMember() {
        return member;
    }

    public void setAuthor(String member) {
        this.member= member;
    }
}
