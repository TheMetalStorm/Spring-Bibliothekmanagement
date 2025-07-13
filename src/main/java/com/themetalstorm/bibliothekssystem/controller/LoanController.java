package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.LoanDTO;
import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.service.LoanService;
import jakarta.websocket.server.PathParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
@RestController
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // TODO: Get User Loans (user)
    // TODO: get all loans (admin)
    // TODO: Add loan (admin)
    // TODO: Edit loan (admin)
    // TODO: Remove loan (admin)

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/loan/{bookId}")
    public ResponseEntity<LoanDTO> loanBook(@PathVariable int bookId, @RequestHeader(name="Authorization") String token) {
        return new ResponseEntity<>(loanService.addLoan(bookId, token), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/return/{loanId}")
    public ResponseEntity<LoanDTO> returnBook(@PathVariable int loanId, @RequestHeader(name="Authorization") String token) {
        return new ResponseEntity<>(loanService.removeLoan(loanId, token), HttpStatus.OK);
    }
}
