package com.tools.potato_field.controller;

import com.tools.potato_field.entity.Item;
import com.tools.potato_field.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.findItem(id);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAllItems();
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
