package com.tools.potato_field.repository;

import com.tools.potato_field.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
