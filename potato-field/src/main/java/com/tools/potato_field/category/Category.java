package com.tools.potato_field.category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;           // 하위 항목 이름 (공릉, 성수, 데일리 등)
    private String categoryType;   // 상위 카테고리 (location 또는 TPO)

    public Category() {
    }

    public Category(String name, String categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }
}