package com.tools.potato_field.post;

import java.util.List;

public class PostRequest {

    private String title;
    private String content;
    private Long memberId;  // Member ID로 받기
    private List<String> imageUrls;  // 이미지 URL 리스트

    // 기본 생성자
    public PostRequest() {}

    // 모든 필드를 포함하는 생성자
    public PostRequest(String title, String content, Long memberId, List<String> imageUrls) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.imageUrls = imageUrls;
    }

    // Getter 및 Setter 메서드

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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}

