package com.tools.potato_field.like;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping
    public ResponseEntity<Likes> addLike(@RequestParam Long memberId, @RequestParam Long postId) {
        Likes likes = likesService.addLike(memberId, postId);
        return ResponseEntity.ok(likes);
    }

    @DeleteMapping
    public ResponseEntity<?> removeLike(@RequestParam Long memberId, @RequestParam Long postId) {
        likesService.removeLike(memberId, postId);
        return ResponseEntity.ok().body("Like removed successfully");
    }
}

