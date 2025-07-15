package com.themetalstorm.bibliothekssystem.repository;

import com.themetalstorm.bibliothekssystem.model.Loan;
import com.themetalstorm.bibliothekssystem.model.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findLoansByUserId(Integer userId);
    Page<Loan> findLoansByUserId(Integer userId, Pageable pageable);

    List<Loan> findLoansByBookId(Integer bookId);

    @Query("SELECT l FROM Loan l " +
            "WHERE (:loanStatus IS NULL OR l.status = :loanStatus) AND " +
            "(:userId IS NULL OR l.userId = :userId) AND " +
            "(:bookId IS NULL OR l.bookId = :bookId)")

    Page<Loan> findBySearch(Integer page, Integer size, String sortField, String sortDirection, Integer userId, Integer bookId, LoanStatus loanStatus, Pageable unpaged);
}