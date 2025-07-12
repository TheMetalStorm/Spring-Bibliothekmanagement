package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.LoanDTO;
import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.service.LoanService;
import jakarta.websocket.server.PathParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//TODO: check permissions once authorization is implemented
//TODO: return Response Entity when appropriate
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
    public LoanDTO loanBook(@PathVariable int bookId, @RequestHeader(name="Authorization") String token) {
        return loanService.addLoan(bookId, token);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/return/{loanId}")
    public LoanDTO returnBook(@PathVariable int loanId, @RequestHeader(name="Authorization") String token) {
        return loanService.removeLoan(loanId, token);
    }
}
