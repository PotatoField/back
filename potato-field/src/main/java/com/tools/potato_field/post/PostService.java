package com.tools.potato_field.post;

import com.tools.potato_field.dto.CommentDto;
import com.tools.potato_field.comment.Comment;
import com.tools.potato_field.category.Category;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.ResourceNotFoundException;
import com.tools.potato_field.postimage.PostImageRepository;
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
    private final PostImageRepository postImageRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, PostImageRepository postImageRepository,
                       MemberRepository memberRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
    }

    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    public Optional<PostDto> getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return Optional.of(mapToDto(post));
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
        post.getComments().add(comment);  // 댓글을 포스트에 추가

        return new CommentDto(comment.getId(), comment.getContent());
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setMemberId(post.getMember().getId());
        postDto.setCategoryId(post.getCategory().getId());
        postDto.setComments(
                post.getComments().stream()
                        .map(comment -> new CommentDto(comment.getId(), comment.getContent()))
                        .collect(Collectors.toList())
        );
        return postDto;
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
