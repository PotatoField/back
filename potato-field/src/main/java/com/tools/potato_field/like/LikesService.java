package com.tools.potato_field.like;

import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import com.tools.potato_field.post.Post;
import com.tools.potato_field.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public LikesService(LikesRepository likesRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.likesRepository = likesRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public Likes addLike(Long memberId, Long postId) {
        Optional<Likes> existingLike = likesRepository.findByMemberIdAndPostId(memberId, postId);
        if (existingLike.isPresent()) {
            throw new IllegalArgumentException("Already liked");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        Likes likes = new Likes();
        likes.setMember(member);  // Member 객체 설정
        likes.setPost(post);  // Post 객체 설정

        return likesRepository.save(likes);
    }

    public void removeLike(Long memberId, Long postId) {
        Optional<Likes> existingLike = likesRepository.findByMemberIdAndPostId(memberId, postId);
        existingLike.ifPresent(likesRepository::delete);
    }
}



