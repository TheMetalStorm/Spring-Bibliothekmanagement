package com.themetalstorm.bibliothekssystem.dto;

import com.themetalstorm.bibliothekssystem.model.Loan;
import com.themetalstorm.bibliothekssystem.model.LoanStatus;

import java.time.LocalDateTime;

public record LoanDTO(Integer id, Integer bookId, Integer userId, LoanStatus loanStatus, LocalDateTime returned) {
    public LoanDTO(Loan loan) {
        this(loan.getId(), loan.getBookId(), loan.getUserId(), loan.getStatus(), loan.getReturned());
    }
}
