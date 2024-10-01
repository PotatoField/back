package com.tools.potato_field.comment;

import com.tools.potato_field.dto.CommentDto;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.member.MemberRepository;
import com.tools.potato_field.post.Post;
import com.tools.potato_field.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());

        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);

        Member member = memberRepository.findById(commentDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        comment.setMember(member);

        Comment savedComment = commentRepository.save(comment);
        return mapToDto(savedComment);
    }

    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setPostId(comment.getPost().getId());
        dto.setMemberId(comment.getMember().getId());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}