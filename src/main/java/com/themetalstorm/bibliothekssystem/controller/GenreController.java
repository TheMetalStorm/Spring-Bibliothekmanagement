package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import com.themetalstorm.bibliothekssystem.dto.GenreDTO;
import com.themetalstorm.bibliothekssystem.model.Genre;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import com.themetalstorm.bibliothekssystem.service.BookService;
import com.themetalstorm.bibliothekssystem.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;
    private final BookRepository bookRepository;

    @Autowired
    GenreController(GenreService genreService, BookRepository bookRepository) {
        this.genreService = genreService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    GenreDTO findById(@PathVariable long id) {
        return genreService.getGenreById(id);
    }

    @PostMapping("")
    @ResponseBody
    void addGenre(@RequestBody GenreDTO genre) {
        genreService.addGenre(genre);
    }

    @DeleteMapping("/{id}")
    void deleteGenreById(@PathVariable long id) {
        genreService.deleteGenreById(id);
    }
    @GetMapping("/{id}/books")
    public List<BookDTO> getBooksByGenreId(@PathVariable long id) {
        return genreService.getBooksByGenreId(id);
    }
}
