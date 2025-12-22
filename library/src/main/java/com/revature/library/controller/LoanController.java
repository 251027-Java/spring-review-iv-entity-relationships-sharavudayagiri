package com.revature.library.controller;

import com.revature.library.model.Loan;
import com.revature.library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LoanController - REST endpoints for managing loans
 */
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestParam Long bookId, @RequestParam Long patronId) {
        Loan loan = loanService.createLoan(bookId, patronId);
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long id) {
        Loan loan = loanService.returnLoan(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Loan>> getActiveLoans() {
        List<Loan> activeLoans = loanService.getActiveLoans();
        return ResponseEntity.ok(activeLoans);
    }

    @GetMapping("/patron/{patronId}")
    public ResponseEntity<List<Loan>> getLoansByPatron(@PathVariable Long patronId) {
        List<Loan> loans = loanService.getLoansByPatron(patronId);
        return ResponseEntity.ok(loans);
    }
}
