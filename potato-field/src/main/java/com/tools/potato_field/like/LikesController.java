package com.tools.potato_field.like;

import com.tools.potato_field.dto.LikesDTO;
import com.tools.potato_field.dto.PostLikesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    //좋아요 추가
    @PostMapping("/posts/{postId}")
    public ResponseEntity<LikesDTO> addLike(@RequestParam Long memberId, @PathVariable Long postId) {
        LikesDTO likesDTO = likesService.addLike(memberId, postId);
        return ResponseEntity.ok(likesDTO);
    }

    //좋아요 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> removeLike(@RequestParam Long memberId, @PathVariable Long postId) {
        likesService.removeLike(memberId, postId);
        return ResponseEntity.ok().body("Like removed successfully");
    }

    // 포스트별 좋아요 개수 조회
    @GetMapping("/posts/{postId}/count")
    public ResponseEntity<PostLikesDTO> getPostLikeCount(@PathVariable Long postId) {
        PostLikesDTO postLikesDTO = likesService.getPostLikeCount(postId);
        return ResponseEntity.ok(postLikesDTO);
    }

    // 사용자가 좋아요한 포스트 목록 조회
    @GetMapping("/members/{memberId}/liked-posts")
    public ResponseEntity<List<PostLikesDTO>> getUserLikedPosts(@PathVariable Long memberId) {
        List<PostLikesDTO> likedPosts = likesService.getUserLikedPosts(memberId);
        return ResponseEntity.ok(likedPosts);
    }
}

