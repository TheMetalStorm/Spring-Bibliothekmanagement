package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.GenreDTO;
import com.themetalstorm.bibliothekssystem.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

//TODO: check permissions once authorization is implemented
//TODO: return Response Entity when appropriate

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("")
    Page<GenreDTO> getAllGenres(@RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer size,
                                @RequestParam(defaultValue = "name") String sortField,
                                @RequestParam(defaultValue = "ASC") String sortDirection) {
        return genreService.getAllGenres(page, size, sortField, sortDirection);
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
