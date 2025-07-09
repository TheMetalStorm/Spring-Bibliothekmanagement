package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import com.themetalstorm.bibliothekssystem.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/{id}/books")
    public List<BookDTO> getBooksByAuthor(@PathVariable long id) {
        return authorService.getBooksByAuthorId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable long id) {
        authorService.deleteById(id);
    }
}
