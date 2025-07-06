package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import com.themetalstorm.bibliothekssystem.dto.GenreDTO;
import com.themetalstorm.bibliothekssystem.model.Author;
import com.themetalstorm.bibliothekssystem.model.Book;
import com.themetalstorm.bibliothekssystem.model.Genre;
import com.themetalstorm.bibliothekssystem.repository.AuthorRepository;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import com.themetalstorm.bibliothekssystem.repository.GenreRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {


    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    BookService(BookRepository bookRepository, AuthorService authorService, GenreService genreService, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public BookDTO getBookById(long id) {
        return bookRepository.findById(id).map(BookDTO::new).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book not found with id: " + id
        ));
    }
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO::new)
                .toList();
    }


    public void addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        // For each author in the book
        for (Author author : book.getAuthors()) {
            // Check if author already exists
            Optional<Author> existingAuthor = authorRepository.findByFirstNameAndLastName(
                    author.getFirstName(),
                    author.getLastName());

            // If exists, use the existing author instead of creating new one
            existingAuthor.ifPresent(a -> {
                book.getAuthors().remove(author);
                book.getAuthors().add(a);
            });
        }

        for (Genre genre : book.getGenres()) {
            // Check if author already exists
            Optional<Genre> existingGenre = genreRepository.findByName(
                    genre.getName());

            // If exists, use the existing author instead of creating new one
            existingGenre.ifPresent(g -> {
                book.getGenres().remove(genre);
                book.getGenres().add(g);
            });
        }


        bookRepository.save(book);
    }

    public void deleteBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book not found with id: " + id
        ));
        bookRepository.delete(book);
    }


}
