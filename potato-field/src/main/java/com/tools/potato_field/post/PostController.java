package com.tools.potato_field.post;

import com.tools.potato_field.like.Like;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam String title, @RequestParam String content) {
        Post post = postService.createPost(title, content);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Like> likePost(@PathVariable Long postId, @RequestParam Long memberId) {
        Like like = postService.addLikeToPost(postId, memberId);
        return ResponseEntity.ok(like);
    }
}
