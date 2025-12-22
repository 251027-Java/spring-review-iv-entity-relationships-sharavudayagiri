package com.revature.library.exception;

public class LoanNotFoundException extends RuntimeException {
    
    public LoanNotFoundException(String message) {
        super(message);
    }
    
    public LoanNotFoundException(Long id) {
        super("Loan not found with id: " + id);
    }
}
