package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.BookPostDTO;
import com.themetalstorm.bibliothekssystem.service.BookService;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    ResponseEntity<Page<BookDTO>> findBySearch(@RequestParam(defaultValue = "") String name,
                               @RequestParam(required = false) Integer genreId,
                               @RequestParam(required = false) Integer authorId,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer size,
                               @RequestParam(defaultValue = "name") String sortField,
                               @RequestParam(defaultValue = "ASC") String sortDirection) {


        return new ResponseEntity<>(bookService.getBookBySearch(name, genreId, authorId, page, size, sortField, sortDirection), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    @ResponseBody
    ResponseEntity<BookDTO> addBook(@RequestBody BookPostDTO book) {
        return new ResponseEntity<>(bookService.addBookPostDTO(book), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody BookPostDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
