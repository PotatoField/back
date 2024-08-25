package com.tools.potato_field.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.tools.potato_field.member.Member;
import com.tools.potato_field.postimage.PostImage;
import com.tools.potato_field.postimage.PostImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    public PostService(PostRepository postRepository, PostImageRepository postImageRepository) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
    }
    //권준영
    public List<Post> getPostsByUserID(String userID, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByMemberUserID(userID, pageable).getContent();
    }//권준영
    @Transactional
    public Post createPostWithImages(String title, String content, Member member, List<String> imageUrls) {
        Post post = new Post(title, content, null, member);  // Category는 null로 임시 설정
        postRepository.save(post);

        for (String imageUrl : imageUrls) {
            PostImage postImage = new PostImage(imageUrl, post);
            postImageRepository.save(postImage);
        }

        return post;
    }
}
