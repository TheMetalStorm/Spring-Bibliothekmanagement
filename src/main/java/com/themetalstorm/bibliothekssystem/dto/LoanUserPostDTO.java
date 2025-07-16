package com.themetalstorm.bibliothekssystem.dto;

import com.themetalstorm.bibliothekssystem.model.Loan;

public record LoanUserPostDTO(Integer bookId) {
    public LoanUserPostDTO(Loan loan) {
        this(loan.getBookId());
    }
}
