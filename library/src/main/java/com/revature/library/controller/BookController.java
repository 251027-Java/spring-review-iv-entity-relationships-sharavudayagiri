package com.revature.library.controller;

import com.revature.library.exception.BookNotFoundException;
import com.revature.library.model.Book;
import com.revature.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController - REST endpoints for managing books
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<Book> checkoutBook(@PathVariable Long id) {
        Book book = bookService.checkoutBook(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long id) {
        Book book = bookService.returnBook(id);
        return ResponseEntity.ok(book);
    }
}
