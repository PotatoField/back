package com.tools.potato_field.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category2")
public class Category2Controller {

    @Autowired
    private Category2Repository category2Repository;

    // Create
    @PostMapping
    public ResponseEntity<Category_2> createCategory2(@RequestBody Category_2 category2) {
        Category_2 savedCategory = category2Repository.save(category2);
        return ResponseEntity.ok(savedCategory);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Category_2> getCategory2(@PathVariable Long id) {
        Optional<Category_2> category2 = category2Repository.findById(id);
        return category2.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<Category_2>> getAllCategories2() {
        List<Category_2> categories = category2Repository.findAll();
        return ResponseEntity.ok(categories);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Category_2> updateCategory2(@PathVariable Long id, @RequestBody Category_2 category2Details) {
        Category_2 category2 = category2Repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

        if (category2Details.getPlaceName() != null) {
            category2.setPlaceName(category2Details.getPlaceName());
        }

        Category_2 updatedCategory = category2Repository.save(category2);
        return ResponseEntity.ok(updatedCategory);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory2(@PathVariable Long id) {
        category2Repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
