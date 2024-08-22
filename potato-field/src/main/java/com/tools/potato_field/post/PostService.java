package com.tools.potato_field.post;

import com.tools.potato_field.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
        return convertToDto(post);
    }

    public PostDto createPost(PostDto postDto) {
        Post post = convertToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setMember(postDto.getMember());

        Post updatedPost = postRepository.save(post);
        return convertToDto(updatedPost);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
        postRepository.delete(post);
    }

    // Entity를 DTO로 변환하는 메서드
    private PostDto convertToDto(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getMember());
    }

    // DTO를 Entity로 변환하는 메서드
    private Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setMember(postDto.getMember());
        return post;
    }
}
