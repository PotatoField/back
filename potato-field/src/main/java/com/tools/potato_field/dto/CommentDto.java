package com.tools.potato_field.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private Long postId;
    private Long memberId;
    private LocalDateTime createdAt;
}