package com.revature.library.exception;

public class PatronNotFoundException extends RuntimeException {
    
    public PatronNotFoundException(String message) {
        super(message);
    }
    
    public PatronNotFoundException(Long id) {
        super("Patron not found with id: " + id);
    }
}
