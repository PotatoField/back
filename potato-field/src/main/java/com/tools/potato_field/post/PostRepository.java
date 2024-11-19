package com.tools.potato_field.post;

import com.tools.potato_field.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByMemberUserID(String userID, Pageable pageable);

    Optional<Post> findById(Long id);
}
