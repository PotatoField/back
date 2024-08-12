package com.tools.potato_field.likes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
//    Optional<Likeke> findByMemberIdAndPostId(Long memberId, Long postId);
}