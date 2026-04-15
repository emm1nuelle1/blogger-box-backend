package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger_box_backend.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API", description = "Endpoints for posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all posts", description = "Retrieve all posts or filter by title/content")
    public ResponseEntity<List<Post>> getAll(@RequestParam(required = false) String value) {
        List<Post> posts = (value == null || value.isBlank()) ? service.getAll() : service.getAllByTitleOrContent(value);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get post by id")
    public ResponseEntity<Post> getById(@PathVariable UUID id) throws PostNotFoundByIdException {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get all posts by category id")
    public ResponseEntity<List<Post>> getAllByCategoryId(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(service.getAllByCategoryId(categoryId));
    }

    @PostMapping
    @Operation(summary = "Create a post")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequest request) throws CategoryNotFoundByIdException {
        Post post = service.create(request.title(), request.content(), request.categoryId());
        return ResponseEntity.created(URI.create("v1/posts/" + post.getId())).body(post);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a post")
    public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody UpdatePostRequest request)
            throws PostNotFoundByIdException, CategoryNotFoundByIdException {
        return ResponseEntity.ok(service.update(id, request.title(), request.content(), request.categoryId()));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a post")
    public ResponseEntity<Boolean> deletePost(@PathVariable UUID id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    public record CreatePostRequest(String title, String content, UUID categoryId) {}
    public record UpdatePostRequest(String title, String content, UUID categoryId) {}
}
