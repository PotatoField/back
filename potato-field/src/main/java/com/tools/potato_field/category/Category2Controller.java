package com.tools.potato_field.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category2")
public class Category2Controller {

    @Autowired
    private Category2Repository category2Repository;

    // Create
    @PostMapping
    public Category_2 createCategory2(@RequestBody Category_2 category2) {
        return category2Repository.save(category2);
    }

    // Read
    @GetMapping("/{id}")
    public Optional<Category_2> getCategory2(@PathVariable Long id) {
        return category2Repository.findById(id);
    }

    // Read All
    @GetMapping
    public List<Category_2> getAllCategories2() {
        return category2Repository.findAll();
    }

    // Update
    @PutMapping("/{id}")
    public Category_2 updateCategory2(@PathVariable Long id, @RequestBody Category_2 category2Details) {
        Category_2 category2 = category2Repository.findById(id).orElseThrow();

        if (category2Details.getPlaceName() != null) {
            category2.setPlaceName(category2Details.getPlaceName());
        }

        return category2Repository.save(category2);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteCategory2(@PathVariable Long id) {
        category2Repository.deleteById(id);
    }
}
