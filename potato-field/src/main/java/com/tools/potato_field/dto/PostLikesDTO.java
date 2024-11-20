package com.tools.potato_field.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikesDTO {
    private Long postId;
    private String postTitle;  // 포스트 제목 또는 관련 정보
    private int likeCount;  // 좋아요 개수
}
