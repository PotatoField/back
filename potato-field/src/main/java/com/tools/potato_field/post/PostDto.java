package com.tools.potato_field.post;

import com.tools.potato_field.dto.CommentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private Long categoryId;
    private List<CommentDto> comments; // 댓글 추가

    // 기본 생성자
    public PostDto() {
    }

    // 모든 필드를 받는 생성자
    public PostDto(Long id, String title, String content, Long memberId, Long categoryId, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.comments = comments;
    }
}
