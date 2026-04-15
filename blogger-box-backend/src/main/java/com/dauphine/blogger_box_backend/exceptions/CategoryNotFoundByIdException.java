package com.dauphine.blogger_box_backend.exceptions;

import java.util.UUID;

public class CategoryNotFoundByIdException extends Exception {
    public CategoryNotFoundByIdException(UUID id) {
        super("Category with id " + id + " not found");
    }
}