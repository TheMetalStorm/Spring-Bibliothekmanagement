package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.dto.GenreDTO;
import com.themetalstorm.bibliothekssystem.model.Book;
import com.themetalstorm.bibliothekssystem.model.Genre;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import com.themetalstorm.bibliothekssystem.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public GenreService(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    public void saveAllGenres(Collection<GenreDTO> genres) {
        List<Genre> list = genres.stream().filter(genreDTO -> !genreRepository.existsByName(genreDTO.name())).map(Genre::new).toList();
        genreRepository.saveAll(list);
    }

    public void deleteAll() {
        genreRepository.deleteAll();
    }

    public void addGenre(GenreDTO genre) {
        genreRepository.save(new Genre(genre));
    }

    public Page<GenreDTO> getAllGenres(int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return genreRepository.findAll(pageable).map(GenreDTO::new);
    }

    public GenreDTO getGenreById(int id) {
        return genreRepository.findById(id).map(GenreDTO::new).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Author not found with id: " + id
        ));
    }

    public void deleteGenreById(int id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Genre not found with id: " + id
        ));
        genreRepository.deleteById(id);
    }
}
