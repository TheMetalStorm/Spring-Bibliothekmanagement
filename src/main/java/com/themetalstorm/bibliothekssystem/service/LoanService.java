package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import com.themetalstorm.bibliothekssystem.dto.LoanDTO;
import com.themetalstorm.bibliothekssystem.model.Book;
import com.themetalstorm.bibliothekssystem.model.Loan;
import com.themetalstorm.bibliothekssystem.model.LoanStatus;
import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import com.themetalstorm.bibliothekssystem.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.themetalstorm.bibliothekssystem.exceptions.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final JWTService jwtService;
    private final MyUserService myUserService;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public LoanService(LoanRepository loanRepository, JWTService jwtService, MyUserService myUserService, BookRepository bookRepository, BookService bookService) {
        this.loanRepository = loanRepository;
        this.jwtService = jwtService;
        this.myUserService = myUserService;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Transactional
    public LoanDTO addLoan(Integer bookId, String token) {
        String jwtToken = token.substring(7);

        String username = jwtService.extractUserName(jwtToken);
        User user = myUserService.loadWholeUserByUsername(username);
        int userId = user.getId();

        List<Loan> loansByUserId = loanRepository.findLoansByUserId(userId);
        boolean alreadyLoaned = loansByUserId.stream().filter(loan -> loan.getStatus() == LoanStatus.LOANED).anyMatch(loan -> Objects.equals(loan.getBookId(), bookId));
        if (alreadyLoaned) {
            throw new ResourceNotFoundException("User already has a copy of this book");
        }

        Book bookById = bookService.getWholeBookById(bookId);

        if (bookById.getAvailableCopies() <= 0) {
            throw new ResourceNotFoundException("No copies of Book " + bookById.getName() + " available");
        }

        Loan saved = loanRepository.save(new Loan(bookId, userId, LoanStatus.LOANED));
        bookById.setAvailableCopies(bookById.getAvailableCopies() - 1);
        bookRepository.saveAndFlush(bookById);

        return new LoanDTO(saved);
    }

    @Transactional
    public LoanDTO removeLoan(Integer loanId, String token) {
        String jwtToken = token.substring(7);

        String username = jwtService.extractUserName(jwtToken);
        User user = myUserService.loadWholeUserByUsername(username);
        int userId = user.getId();


        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan does not exist in our system"));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new ResourceNotFoundException("Loan has already been returned");
        }

        if (loan.getUserId() != userId) {
            throw new ResourceNotFoundException("You are not allowed to delete this loan");
        }
        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturned(LocalDateTime.now());

        Book bookById = bookService.getWholeBookById(loan.getBookId());
        bookById.setAvailableCopies(bookById.getAvailableCopies() + 1);

        bookRepository.saveAndFlush(bookById);
        Loan toReturn = loanRepository.saveAndFlush(loan);
        return new LoanDTO(toReturn);
    }
}
