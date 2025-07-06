package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.model.Author;
import com.themetalstorm.bibliothekssystem.model.Book;
import com.themetalstorm.bibliothekssystem.repository.AuthorRepository;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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



    public void addBook(BookDTO book) {
        if(book.authors() != null && !book.authors().isEmpty()) {
            List<Author> authors = book.authors().stream().map(Author::new).toList();
            authorRepository.saveAll(authors);
        }
        bookRepository.save(new Book(book));
    }


    public void deleteBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book not found with id: " + id
        ));
        bookRepository.delete(book);
    }
}
