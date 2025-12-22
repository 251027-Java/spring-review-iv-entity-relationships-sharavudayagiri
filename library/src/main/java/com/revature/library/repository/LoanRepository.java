package com.revature.library.repository;

import com.revature.library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    List<Loan> findByReturnDateIsNull();
    
    List<Loan> findByPatronId(Long patronId);
}
