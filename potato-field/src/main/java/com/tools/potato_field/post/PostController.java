package com.tools.potato_field.post;

import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;

    public PostController(PostService postService, MemberRepository memberRepository) {
        this.postService = postService;
        this.memberRepository = memberRepository;
    }

    @PostMapping
    public Post createPost(@RequestBody PostRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return postService.createPostWithImages(
                request.getTitle(),
                request.getContent(),
                member,
                request.getImageUrls()
        );
    }
}
