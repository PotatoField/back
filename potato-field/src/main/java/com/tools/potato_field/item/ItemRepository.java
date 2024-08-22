package com.tools.potato_field.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPostId(Long postId);

    Page<Item> findAll(Pageable pageable); // 페이징 처리
}
