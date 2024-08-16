package com.tools.potato_field.post;

import com.tools.potato_field.like.Likes;
import com.tools.potato_field.like.LikesRepository;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikesRepository likeRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, LikesRepository likeRepository, MemberRepository memberRepository) {
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
}