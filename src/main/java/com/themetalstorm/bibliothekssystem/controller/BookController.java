package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.service.BookService;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    BookDTO findById(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("")
    @ResponseBody
    void addBook(@RequestBody BookDTO book) {
        bookService.addBook(book);
    }

    //TODO: PUT

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id);
    }
}
