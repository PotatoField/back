package com.tools.potato_field.item;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.tools.potato_field.member.Member;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create
    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item, @AuthenticationPrincipal Member member) {
        item.setMember(member); // 현재 로그인한 멤버를 아이템의 소유자로 설정
        Item savedItem = itemService.addItem(item);
        return ResponseEntity.ok(savedItem);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Item item = itemService.findItem(id);
        return ResponseEntity.ok(item);
    }

    // Read All with Pagination
    @GetMapping
    public ResponseEntity<Page<Item>> getAllItems(Pageable pageable) {
        Page<Item> items = itemService.findAllItems(pageable);
        return ResponseEntity.ok(items);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @Valid @RequestBody Item itemDetails) {
        Item item = itemService.findItem(id);

        if (itemDetails.getItemName() != null) {
            item.setItemName(itemDetails.getItemName());
        }
        if (itemDetails.getItemURL() != null) {
            item.setItemURL(itemDetails.getItemURL());
        }
        if (itemDetails.getIconNumber() != null) {
            item.setIconNumber(itemDetails.getIconNumber());
        }
        Item updatedItem = itemService.addItem(item);
        return ResponseEntity.ok(updatedItem);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
