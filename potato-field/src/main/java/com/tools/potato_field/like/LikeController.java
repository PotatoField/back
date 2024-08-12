package com.tools.potato_field.like;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> addLike(@RequestParam Long memberId, @RequestParam Long postId) {
        Like like = likeService.addLike(memberId, postId);
        return ResponseEntity.ok(like);
    }

    @DeleteMapping
    public ResponseEntity<?> removeLike(@RequestParam Long memberId, @RequestParam Long postId) {
        likeService.removeLike(memberId, postId);
        return ResponseEntity.ok().body("Like removed successfully");
    }
}

