package com.tools.potato_field.post;

import com.tools.potato_field.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    // 생성자 주입 방식으로 PostService를 주입합니다.
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 1. 포스트 생성 API (POST 요청)
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto createdPost = postService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // 2. 포스트 조회 API (GET 요청) - 특정 ID로 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    // 3. 포스트 목록 조회 API (GET 요청) - 전체 조회
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // 4. 포스트 수정 API (PUT 요청)
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(id, postDto);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // 5. 포스트 삭제 API (DELETE 요청)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

