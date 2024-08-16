package com.tools.potato_field.post;

import com.tools.potato_field.like.Like;
import com.tools.potato_field.like.LikeRepository;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, LikeRepository likeRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.memberRepository = memberRepository;
    }

    public Post createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    public Like addLikeToPost(Long postId, Long memberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Like like = new Like();
        like.setPost(post);
        like.setMember(member);  // Member 객체를 설정합니다.

        return likeRepository.save(like);
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }
}

