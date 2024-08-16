package com.tools.potato_field.like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberIdAndPostId(Long memberId, Long postId);
}

