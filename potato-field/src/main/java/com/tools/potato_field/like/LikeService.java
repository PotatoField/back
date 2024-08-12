package com.tools.potato_field.like;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like addLike(Long memberId, Long postId) {
        Optional<Like> existingLike = likeRepository.findByMemberIdAndPostId(memberId, postId);
        if (existingLike.isPresent()) {
            throw new IllegalArgumentException("Already liked");
        }

        Like like = new Like();
        like.setMemberId(memberId);
        like.setPostId(postId);

        return likeRepository.save(like);
    }

    public void removeLike(Long memberId, Long postId) {
        Optional<Like> existingLike = likeRepository.findByMemberIdAndPostId(memberId, postId);
        existingLike.ifPresent(likeRepository::delete);
    }
}

