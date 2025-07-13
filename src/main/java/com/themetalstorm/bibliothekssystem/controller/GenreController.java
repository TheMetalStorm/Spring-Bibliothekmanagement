package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.dto.GenreDTO;
import com.themetalstorm.bibliothekssystem.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("")
    ResponseEntity<Page<GenreDTO>> getAllGenres(@RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer size,
                                @RequestParam(defaultValue = "name") String sortField,
                                @RequestParam(defaultValue = "ASC") String sortDirection) {
        return new ResponseEntity<>(genreService.getAllGenres(page, size, sortField, sortDirection), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<GenreDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(genreService.getGenreById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    @ResponseBody
    ResponseEntity<Void> addGenre(@RequestBody GenreDTO genre) {
        genreService.addGenre(genre);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable int id, @RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.updateGenre(id, genreDTO));
    }

    //TODO: PUT

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGenreById(@PathVariable int id) {
        genreService.deleteGenreById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
