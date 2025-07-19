package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.LoanDTO;
import com.themetalstorm.bibliothekssystem.model.LoanStatus;
import com.themetalstorm.bibliothekssystem.service.LoanService;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/loans/{bookId}")
    public ResponseEntity<LoanDTO> loanBook(@PathVariable int bookId, @RequestHeader(name="Authorization") String token) {
        return new ResponseEntity<>(loanService.addLoan(bookId, token), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/return/{loanId}")
    public ResponseEntity<LoanDTO> returnBook(@PathVariable int loanId, @RequestHeader(name="Authorization") String token) {
        return new ResponseEntity<>(loanService.returnLoan(loanId, token), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/loans")
    public ResponseEntity<Page<LoanDTO>> getAllLoans(@RequestParam(required = false) LoanStatus loanStatus,
                                                     @RequestParam(required = false) Integer userId,
                                                     @RequestParam(required = false) Integer bookId,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size,
                                                     @RequestParam(defaultValue = "id") String sortField,
                                                     @RequestParam(defaultValue = "ASC") String sortDirection) {
        return new ResponseEntity<>(loanService.getAllLoans(page, size, sortField, sortDirection, userId, bookId, loanStatus), HttpStatus.OK);
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/loans")
    public ResponseEntity<LoanDTO> addLoan(@RequestParam(required = true) Integer userId, @RequestParam(required = true) Integer bookId) {
        return new ResponseEntity<>(loanService.addLoanAdmin(userId, bookId), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/loans/raw")
    public ResponseEntity<LoanDTO> addLoanBypassChecks(@RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.addLoanAdminBypassChecks(loanDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/loans/{loanId}")
    public ResponseEntity<LoanDTO> editLoan(@PathVariable Integer loanId, @RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.editLoan(loanId, loanDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/return/{loanId}")
    public ResponseEntity<LoanDTO> returnLoanAdmin(@PathVariable Integer loanId) {
        return new ResponseEntity<>(loanService.returnLoanAdmin(loanId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/loans/{loanId}")
    public ResponseEntity<LoanDTO> removeLoan(@PathVariable Integer loanId) {
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
