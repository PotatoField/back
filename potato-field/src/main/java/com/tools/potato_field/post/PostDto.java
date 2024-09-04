package com.tools.potato_field.post;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}