package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.dto.GenreDTO;
import com.themetalstorm.bibliothekssystem.exceptions.ResourceAlreadyExistsException;
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
import com.themetalstorm.bibliothekssystem.exceptions.ResourceNotFoundException;

import java.util.Collection;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void addGenre(GenreDTO genre) {
        if (genreRepository.existsByName(genre.name())) {
            throw new ResourceAlreadyExistsException("Genre with name " + genre.name() + " already exists");
        }
        genreRepository.save(new Genre(genre));
    }

    public Page<GenreDTO> getAllGenres(Integer page, Integer size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);

        Page<Genre> all;
        if(page == null || size == null) {
            all = genreRepository.findAll(Pageable.unpaged(sort));
        }
        else{
            Pageable pageable = PageRequest.of(page, size, sort);
            all = genreRepository.findAll(pageable);

        }
        return all.map(GenreDTO::new);
    }

    public GenreDTO getGenreById(int id) {
        return genreRepository.findById(id).map(GenreDTO::new).orElseThrow(() -> new ResourceNotFoundException(
                "Genre not found with id: " + id
        ));
    }

    public GenreDTO updateGenre(int id, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
        genre.setName(genreDTO.name());
        genre.setDescription(genreDTO.description());
        return new GenreDTO(genreRepository.save(genre));
    }

    public void deleteGenreById(int id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Genre not found with id: " + id
        ));
        genreRepository.deleteById(id);
    }
}
