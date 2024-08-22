package com.tools.potato_field.postimage;

import com.tools.potato_field.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post-images")
public class PostImageController {

    @Autowired
    private PostImageRepository postImageRepository;

    @GetMapping
    public List<PostImage> getAllPostImages() {
        return postImageRepository.findAll();
    }

    @GetMapping("/{id}")
    public PostImage getPostImageById(@PathVariable Long id) {
        return postImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PostImage not found with id " + id));
    }

    @PostMapping
    public PostImage createPostImage(@RequestBody PostImage postImage) {
        return postImageRepository.save(postImage);
    }

    @DeleteMapping("/{id}")
    public void deletePostImage(@PathVariable Long id) {
        PostImage postImage = postImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PostImage not found with id " + id));

        postImageRepository.delete(postImage);
    }
}

