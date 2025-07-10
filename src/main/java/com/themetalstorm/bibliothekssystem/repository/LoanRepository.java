package com.themetalstorm.bibliothekssystem.repository;

import com.themetalstorm.bibliothekssystem.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findLoansByUserId(Integer userId);
    List<Loan> findLoansByBookId(Integer bookId);
}