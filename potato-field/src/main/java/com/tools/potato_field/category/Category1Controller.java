package com.tools.potato_field.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category1")
public class Category1Controller {

    @Autowired
    private Category1Repository category1Repository;

    // Create
    @PostMapping
    public Category_1 createCategory1(@RequestBody Category_1 category1) {
        return category1Repository.save(category1);
    }

    // Read
    @GetMapping("/{id}")
    public Optional<Category_1> getCategory1(@PathVariable Long id) {
        return category1Repository.findById(id);
    }

    // Read All
    @GetMapping
    public List<Category_1> getAllCategories1() {
        return category1Repository.findAll();
    }

    // Update
    @PutMapping("/{id}")
    public Category_1 updateCategory1(@PathVariable Long id, @RequestBody Category_1 category1Details) {
        Category_1 category1 = category1Repository.findById(id).orElseThrow();

        if (category1Details.getGenderName() != null) {
            category1.setGenderName(category1Details.getGenderName());
        }

        return category1Repository.save(category1);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteCategory1(@PathVariable Long id) {
        category1Repository.deleteById(id);
    }
}
