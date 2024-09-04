package com.tools.potato_field.post;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.tools.potato_field.ResourceNotFoundException;
import com.tools.potato_field.postimage.PostImageRepository;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    public PostService(PostRepository postRepository, PostImageRepository postImageRepository) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
    }

    // 1. Post 생성
    public PostDto createPost(PostDto postDto) {
        // PostDto를 Post 엔티티로 변환
        Post post = mapToEntity(postDto);
        // Post 엔티티를 저장
        Post savedPost = postRepository.save(post);
        // 저장된 Post 엔티티를 다시 PostDto로 변환하여 반환
        return mapToDto(savedPost);
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }

    // 2. 특정 ID로 Post 조회
    public Optional<PostDto> getPostById(Long id) {
        // ID로 Post 엔티티 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        // 조회된 Post 엔티티를 PostDto로 변환하여 반환
        return Optional.of(mapToDto(post));
    }

    // 3. 모든 Post 조회
    public List<PostDto> getAllPosts() {
        // 모든 Post 엔티티 조회
        List<Post> posts = postRepository.findAll();
        // 조회된 모든 Post 엔티티를 PostDto 리스트로 변환하여 반환
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // 5. Post 삭제
    public void deletePost(Long id) {
        // ID로 Post 엔티티 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        // Post 엔티티 삭제
        postRepository.delete(post);
    }

    // Post 엔티티를 PostDto로 변환하는 헬퍼 메서드
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setMemberId(post.getMember().getId());
        postDto.setCategoryId(post.getCategory().getId());
        return postDto;
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    } // 4. Post 수정


    public List<Post> getPostsByUserID(String userID, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByMemberUserID(userID, pageable).getContent();
    }//권준영
}