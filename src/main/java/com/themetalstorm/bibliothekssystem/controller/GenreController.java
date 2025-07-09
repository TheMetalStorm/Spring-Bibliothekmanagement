package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.GenreDTO;
import com.themetalstorm.bibliothekssystem.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("")
    List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    GenreDTO findById(@PathVariable int id) {
        return genreService.getGenreById(id);
    }

    @PostMapping("")
    @ResponseBody
    void addGenre(@RequestBody GenreDTO genre) {
        genreService.addGenre(genre);
    }

    //TODO: PUT

    @DeleteMapping("/{id}")
    void deleteGenreById(@PathVariable int id) {
        genreService.deleteGenreById(id);
    }

}
