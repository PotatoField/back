package com.tools.potato_field.post;

import com.tools.potato_field.comment.CommentRepository;
import com.tools.potato_field.dto.CommentDto;
import com.tools.potato_field.post.PostDto;
import com.tools.potato_field.comment.Comment;
import com.tools.potato_field.category.Category;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.ResourceNotFoundException;
import com.tools.potato_field.member.MemberRepository;
import com.tools.potato_field.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository,
                       CategoryRepository categoryRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }

    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    public Optional<PostDto> getPostById(Long id) {
        return postRepository.findById(id).map(this::mapToDto);
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(findCategoryById(postDto.getCategoryId()));
        post.setMember(findMemberById(postDto.getMemberId()));

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        postRepository.delete(post);
    }

    @Transactional
    public CommentDto addComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        if (post.getComments() == null) {
            post.setComments(List.of(savedComment)); // 댓글 리스트 초기화 후 추가
        } else {
            post.getComments().add(savedComment);
        }

        return mapToCommentDto(savedComment);
    }

    private PostDto mapToDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getId(),
                post.getCategory().getId(),
                post.getComments().stream()
                        .map(this::mapToCommentDto)
                        .collect(Collectors.toList())
        );
    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setPostId(comment.getPost().getId());
        dto.setMemberId(comment.getMember() != null ? comment.getMember().getId() : null);
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(findCategoryById(postDto.getCategoryId()));
        post.setMember(findMemberById(postDto.getMemberId()));
        return post;
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
    }
}
