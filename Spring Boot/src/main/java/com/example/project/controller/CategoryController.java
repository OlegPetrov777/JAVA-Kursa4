package com.example.project.controller;

import com.example.project.entity.Category;
import com.example.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/api/category")
    public ResponseEntity<?> create(@RequestBody Category category) {
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/category")
    public ResponseEntity<?> findAll() {
        final List<Category> categoryList = categoryService.findAll();

        return categoryList != null && !categoryList.isEmpty()
                ? new ResponseEntity<>(categoryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/category/{id}")
    public ResponseEntity<Optional<Category>> find(@PathVariable(name = "id") Long id) {
        final Optional<Category> category = categoryService.find(id);

        return category.isPresent()
                ? new ResponseEntity<>(category, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/api/category/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final Optional<Category> category = categoryService.find(id);
        categoryService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
