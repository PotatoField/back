package com.tools.potato_field.post;

import com.tools.potato_field.comment.CommentRepository;
import com.tools.potato_field.dto.CommentDto;
import com.tools.potato_field.comment.Comment;
import com.tools.potato_field.category.Category;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.ResourceNotFoundException;
import com.tools.potato_field.postimage.PostImageRepository;
import com.tools.potato_field.member.MemberRepository;
import com.tools.potato_field.category.CategoryRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    }

    /*public PostService(PostRepository postRepository, PostImageRepository postImageRepository) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
    }*/

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

        // 댓글 객체 생성
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);

        // 댓글 저장
        Comment savedComment = commentRepository.save(comment);

        // Post 엔티티에 댓글 추가
        if (post.getComments() == null) {
            post.setComments(List.of(savedComment)); // 댓글 리스트 초기화 후 추가
        } else {
            post.getComments().add(savedComment);
        }

        return mapToCommentDto(savedComment);
    }

    private PostDto mapToDto(Post post) {

    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId()); // 댓글 ID 설정
        dto.setContent(comment.getContent()); // 댓글 내용 설정
        dto.setPostId(comment.getPost().getId()); // 댓글이 속한 Post ID 설정
        dto.setMemberId(comment.getMember() != null ? comment.getMember().getId() : null); // 댓글 작성자 ID 설정 (작성자가 있을 경우만)
        dto.setCreatedAt(comment.getCreatedAt()); // 댓글 작성 시간 설정
        return dto;
    }


    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

}
