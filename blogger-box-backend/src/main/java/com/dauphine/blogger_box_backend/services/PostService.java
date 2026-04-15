package com.dauphine.blogger_box_backend.services;

import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger_box_backend.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger_box_backend.models.Post;
import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAll();
    List<Post> getAllByTitleOrContent(String value);
    List<Post> getAllByCategoryId(UUID categoryId);
    Post getById(UUID id) throws PostNotFoundByIdException;
    Post create(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException;
    Post update(UUID id, String title, String content, UUID categoryId) throws PostNotFoundByIdException, CategoryNotFoundByIdException;
    boolean deleteById(UUID id);
}
