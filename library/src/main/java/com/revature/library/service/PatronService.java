package com.revature.library.service;

import com.revature.library.exception.PatronNotFoundException;
import com.revature.library.model.Patron;
import com.revature.library.repository.PatronRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> findById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new PatronNotFoundException(id));
    }
}
