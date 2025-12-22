package com.revature.library.controller;

import com.revature.library.exception.PatronNotFoundException;
import com.revature.library.model.Patron;
import com.revature.library.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PatronController - REST endpoints for managing patrons
 */
@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();
        return ResponseEntity.ok(patrons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getById(@PathVariable Long id) {
        Patron patron = patronService.findById(id)
                .orElseThrow(() -> new PatronNotFoundException(id));
        return ResponseEntity.ok(patron);
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody Patron patron) {
        Patron savedPatron = patronService.addPatron(patron);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatron);
    }
}
