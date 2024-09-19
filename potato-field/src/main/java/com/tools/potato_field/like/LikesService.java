package com.tools.potato_field.like;

import com.tools.potato_field.dto.LikesDTO;
import com.tools.potato_field.dto.PostLikesDTO;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import com.tools.potato_field.post.Post;
import com.tools.potato_field.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public LikesService(LikesRepository likesRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.likesRepository = likesRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    // 좋아요 추가
    public LikesDTO addLike(Long memberId, Long postId) {
        Optional<Likes> existingLike = likesRepository.findByMemberIdAndPostId(memberId, postId);
        if (existingLike.isPresent()) {
            throw new IllegalArgumentException("Already liked");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        Likes likes = new Likes();
        likes.setMember(member);
        likes.setPost(post);

        Likes savedLike = likesRepository.save(likes);
        return mapToDTO(savedLike);
    }

    // 좋아요 삭제
    public void removeLike(Long memberId, Long postId) {
        Optional<Likes> existingLike = likesRepository.findByMemberIdAndPostId(memberId, postId);
        existingLike.ifPresent(likesRepository::delete);
    }

    // 포스트별 좋아요 개수 조회
    public PostLikesDTO getPostLikeCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        int likeCount = likesRepository.countByPostId(postId);

        PostLikesDTO postLikesDTO = new PostLikesDTO();
        postLikesDTO.setPostId(postId);
        postLikesDTO.setPostTitle(post.getTitle());  // 포스트의 제목을 설정
        postLikesDTO.setLikeCount(likeCount);

        return postLikesDTO;
    }

    // 사용자가 좋아요한 포스트 목록 조회
    public List<PostLikesDTO> getUserLikedPosts(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        List<Likes> likedPosts = likesRepository.findByMemberId(memberId);

        return likedPosts.stream()
                .map(like -> {
                    PostLikesDTO dto = new PostLikesDTO();
                    dto.setPostId(like.getPost().getId());
                    dto.setPostTitle(like.getPost().getTitle());  // 포스트 제목
                    dto.setLikeCount(likesRepository.countByPostId(like.getPost().getId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private LikesDTO mapToDTO(Likes likes) {
        LikesDTO dto = new LikesDTO();
        dto.setId(likes.getId());
        dto.setMemberId(likes.getMember().getId());
        dto.setPostId(likes.getPost().getId());
        return dto;
    }
}
