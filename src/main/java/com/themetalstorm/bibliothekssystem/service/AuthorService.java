package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import com.themetalstorm.bibliothekssystem.model.Author;
import com.themetalstorm.bibliothekssystem.repository.AuthorRepository;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }
    public void saveAuthor(AuthorDTO author) {
        authorRepository.save(new Author(author));
    }

    public void saveAllAuthors(Collection<AuthorDTO> authors) {
        List<Author> list = authors.stream().filter(authorDTO -> !authorRepository.existsByFirstNameAndLastName(authorDTO.firstName(), authorDTO.lastName())).map(Author::new).toList();
        authorRepository.saveAll(list);
    }

    public void deleteAll() {
        authorRepository.deleteAll();
    }

    public AuthorDTO getAuthorById(long id) {
        return authorRepository.findById(id).map(AuthorDTO::new).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Author not found with id: " + id
        ));
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(AuthorDTO::new).toList();
    }

    public List<BookDTO> getBooksByAuthorId(long id) {
        return bookRepository.findByAuthors_Id(id).stream().map(BookDTO::new).toList();
    }

    //TODO: PUT

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
