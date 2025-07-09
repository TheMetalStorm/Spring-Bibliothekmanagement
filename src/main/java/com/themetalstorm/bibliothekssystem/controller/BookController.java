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
    BookDTO findById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/search")
    List<BookDTO> findBySearch(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Integer genreId,
                               @RequestParam(required = false) Integer authorId)
         {
        return bookService.getBookBySearch(name, genreId, authorId);
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
