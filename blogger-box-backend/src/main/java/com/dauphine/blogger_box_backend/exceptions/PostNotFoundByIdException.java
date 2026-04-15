package com.dauphine.blogger_box_backend.exceptions;

import java.util.UUID;

public class PostNotFoundByIdException extends Exception {
    public PostNotFoundByIdException(UUID id) {
        super("Post with id " + id + " not found");
    }
}
