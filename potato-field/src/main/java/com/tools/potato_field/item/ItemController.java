package com.tools.potato_field.item;

import com.tools.potato_field.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // Create
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    // Read
    @GetMapping("/{id}")
    public Optional<Item> getItem(@PathVariable Long id) {
        return itemRepository.findById(id);
    }

    // Read All
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Update
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Item item = itemRepository.findById(id).orElseThrow();

        if (itemDetails.getItemName() != null) {
            item.setItemName(itemDetails.getItemName());
        }

        if (itemDetails.getItemURL() != null) {
            item.setItemURL(itemDetails.getItemURL());
        }

        if (itemDetails.getIconNumber() != null) {
            item.setIconNumber(itemDetails.getIconNumber());
        }

        if (itemDetails.getId2() != null) {
            item.setId2(itemDetails.getId2());
        }

        if (itemDetails.getMember() != null) {
            item.setMember(itemDetails.getMember());
        }

        return itemRepository.save(item);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }
}

