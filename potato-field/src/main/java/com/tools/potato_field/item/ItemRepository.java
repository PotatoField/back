package com.tools.potato_field.item;

import com.tools.potato_field.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
