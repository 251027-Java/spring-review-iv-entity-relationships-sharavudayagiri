package com.revature.library.service;

import com.revature.library.exception.BookNotAvailableException;
import com.revature.library.exception.BookNotFoundException;
import com.revature.library.exception.LoanNotFoundException;
import com.revature.library.exception.PatronNotFoundException;
import com.revature.library.model.Book;
import com.revature.library.model.Loan;
import com.revature.library.model.Patron;
import com.revature.library.repository.BookRepository;
import com.revature.library.repository.LoanRepository;
import com.revature.library.repository.PatronRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @Transactional
    public Loan createLoan(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        
        if (!book.isAvailable()) {
            throw new BookNotAvailableException(bookId);
        }
        
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException(patronId));
        
        book.setAvailable(false);
        bookRepository.save(book);
        
        Loan loan = new Loan(book, patron);
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId));
        
        loan.setReturnDate(LocalDate.now());
        
        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);
        
        return loanRepository.save(loan);
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnDateIsNull();
    }

    public List<Loan> getLoansByPatron(Long patronId) {
        return loanRepository.findByPatronId(patronId);
    }
}
