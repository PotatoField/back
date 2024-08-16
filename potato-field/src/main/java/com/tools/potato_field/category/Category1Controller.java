package com.tools.potato_field.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category1")
public class Category1Controller {

    @Autowired
    private Category1Repository category1Repository;

    // Create
    @PostMapping
    public ResponseEntity<Category_1> createCategory1(@RequestBody Category_1 category1) {
        Category_1 savedCategory = category1Repository.save(category1);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<Category_1> getCategory1(@PathVariable Long id) {
        Optional<Category_1> category1 = category1Repository.findById(id);
        return category1.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<Category_1>> getAllCategories1() {
        List<Category_1> categories = category1Repository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Category_1> updateCategory1(@PathVariable Long id, @RequestBody Category_1 category1Details) {
        Optional<Category_1> category1Optional = category1Repository.findById(id);

        if (category1Optional.isPresent()) {
            Category_1 category1 = category1Optional.get();

            if (category1Details.getGenderName() != null) {
                category1.setGenderName(category1Details.getGenderName());
            }

            Category_1 updatedCategory = category1Repository.save(category1);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory1(@PathVariable Long id) {
        if (category1Repository.existsById(id)) {
            category1Repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
