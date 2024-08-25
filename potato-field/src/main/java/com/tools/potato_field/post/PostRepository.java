package com.tools.potato_field.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
   //권준영
    Page<Post> findByMemberUserID(String userID, Pageable pageable);
    //권준영
}
