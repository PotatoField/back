package com.tools.potato_field.post;

import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;

    public PostController(PostService postService, MemberRepository memberRepository) {
        this.postService = postService;
        this.memberRepository = memberRepository;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Post post = postService.createPostWithImages(
                request.getTitle(),
                request.getContent(),
                member,
                request.getImageUrls()
        );

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
