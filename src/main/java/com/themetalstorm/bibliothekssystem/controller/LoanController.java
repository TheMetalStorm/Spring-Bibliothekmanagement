package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.LoanDTO;
import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.service.LoanService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loan/{bookId}")
    public LoanDTO loanBook(@PathVariable int bookId, @RequestHeader(name="Authorization") String token) {
        return loanService.addLoan(bookId, token);
    }

    @PostMapping("/return/{loanId}")
    public LoanDTO returnBook(@PathVariable int loanId, @RequestHeader(name="Authorization") String token) {
        return loanService.removeLoan(loanId, token);
    }
}
