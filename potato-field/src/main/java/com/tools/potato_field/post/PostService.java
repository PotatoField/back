package com.tools.potato_field.post;

import com.tools.potato_field.like.Likes;
import com.tools.potato_field.like.LikesRepository;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, LikesRepository likesRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.likesRepository = likesRepository;
        this.memberRepository = memberRepository;
    }

    public Post createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    public Likes addLikeToPost(Long postId, Long memberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Likes likes = new Likes();
        likes.setPost(post);
        likes.setMember(member);  // Member 객체를 설정합니다.
//        post.().add(likes);

        return likesRepository.save(likes);
    }
}

