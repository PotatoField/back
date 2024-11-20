package com.tools.potato_field.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberIdAndPostId(Long memberId, Long postId);
    int countByPostId(Long postId);  // 포스트별 좋아요 개수 조회
    List<Likes> findByMemberId(Long memberId);  // 사용자가 좋아요한 포스트 목록 조회
}
