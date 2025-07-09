package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.service.BookService;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    Page<BookDTO> findBySearch(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) Integer genreId,
                               @RequestParam(required = false) Integer authorId,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer size,
                               @RequestParam(defaultValue = "name") String sortField,
                               @RequestParam(defaultValue = "ASC") String sortDirection) {


        return bookService.getBookBySearch(name, genreId, authorId, page, size, sortField, sortDirection);
    }

    @GetMapping("/{id}")
    BookDTO findById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping("")
    @ResponseBody
    BookDTO addBook(@RequestBody BookDTO book) {
        return bookService.addBook(book);
    }

    //TODO: PUT

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
    }
}
