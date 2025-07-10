package com.themetalstorm.bibliothekssystem.dto;

import com.themetalstorm.bibliothekssystem.model.Loan;
import com.themetalstorm.bibliothekssystem.model.LoanStatus;

import java.time.LocalDateTime;

public record LoanPostDTO(Integer bookId) {
    public LoanPostDTO(Loan loan) {
        this(loan.getBookId());
    }
}
