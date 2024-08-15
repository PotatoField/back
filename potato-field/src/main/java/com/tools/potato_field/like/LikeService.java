package com.tools.potato_field.like;

import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import com.tools.potato_field.post.Post;
import com.tools.potato_field.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public Like addLike(Long memberId, Long postId) {
        Optional<Like> existingLike = likeRepository.findByMemberIdAndPostId(memberId, postId);
        if (existingLike.isPresent()) {
            throw new IllegalArgumentException("Already liked");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        Like like = new Like();
        like.setMember(member);  // Member 객체 설정
        like.setPost(post);  // Post 객체 설정

        return likeRepository.save(like);
    }

    public void removeLike(Long memberId, Long postId) {
        Optional<Like> existingLike = likeRepository.findByMemberIdAndPostId(memberId, postId);
        existingLike.ifPresent(likeRepository::delete);
    }
}



