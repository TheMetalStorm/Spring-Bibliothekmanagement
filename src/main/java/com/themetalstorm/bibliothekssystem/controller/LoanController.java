package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.LoanDTO;
import com.themetalstorm.bibliothekssystem.model.LoanStatus;
import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.service.LoanService;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/loans")
    public ResponseEntity<Page<LoanDTO>> getUserLoans(@RequestHeader(name="Authorization") String token,
                                                      @RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size,
                                                      @RequestParam(defaultValue = "id") String sortField,
                                                      @RequestParam(defaultValue = "ASC") String sortDirection) {
        return new ResponseEntity<>(loanService.getUserLoans(token, page, size, sortField, sortDirection), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/loans")
    public ResponseEntity<Page<LoanDTO>> getAllLoans(@RequestHeader(name="Authorization") String token,
                                                     @RequestParam(required = false) LoanStatus loanStatus,
                                                     @RequestParam(required = false) Integer userId,
                                                     @RequestParam(required = false) Integer bookId,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size,
                                                     @RequestParam(defaultValue = "id") String sortField,
                                                     @RequestParam(defaultValue = "ASC") String sortDirection) {
        return new ResponseEntity<>(loanService.getAllLoans(token, page, size, sortField, sortDirection, userId, bookId, loanStatus), HttpStatus.OK);
    }



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
