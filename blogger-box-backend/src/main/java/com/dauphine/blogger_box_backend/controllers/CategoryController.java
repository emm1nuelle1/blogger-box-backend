package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/v1/categories", "/categories"})
@Tag(name = "Category API", description = "Endpoints for categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieve all categories or filter by name")
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = (name == null || name.isBlank()) ? service.getAll() : service.getAllLikeName(name);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get category by id")
    public ResponseEntity<Category> retrieveCategoryById(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a category")
    public ResponseEntity<Category> createCategory(@RequestBody String name) {
        Category category = service.create(name);
        return ResponseEntity.created(URI.create("v1/categories/" + category.getId())).body(category);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a category")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody String name) throws CategoryNotFoundByIdException {
        return ResponseEntity.ok(service.updateName(id, name));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a category")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable UUID id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
