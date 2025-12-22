package com.revature.library.exception;

public class BookNotAvailableException extends RuntimeException {
    
    public BookNotAvailableException(String message) {
        super(message);
    }
    
    public BookNotAvailableException(Long id) {
        super("Book not available for checkout with id: " + id);
    }
}
