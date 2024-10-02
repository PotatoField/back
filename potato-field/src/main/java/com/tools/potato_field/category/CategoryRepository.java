package com.tools.potato_field.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryType(String categoryType);  // 상위 카테고리(location, TPO) 별로 하위 항목을 조회
}