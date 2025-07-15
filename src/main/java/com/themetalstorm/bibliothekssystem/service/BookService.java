package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import com.themetalstorm.bibliothekssystem.dto.BookPostDTO;
import com.themetalstorm.bibliothekssystem.dto.GenreDTO;
import com.themetalstorm.bibliothekssystem.exceptions.ResourceAlreadyExistsException;
import com.themetalstorm.bibliothekssystem.model.Author;
import com.themetalstorm.bibliothekssystem.model.Book;
import com.themetalstorm.bibliothekssystem.model.Genre;
import com.themetalstorm.bibliothekssystem.repository.AuthorRepository;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import com.themetalstorm.bibliothekssystem.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.themetalstorm.bibliothekssystem.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
@Transactional
public class BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public BookDTO getBookById(int id) {
        return bookRepository.findById(id).map(BookDTO::new).orElseThrow(() -> new ResourceNotFoundException(
                "Book not found with id: " + id
        ));
    }

    public Book getWholeBookById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Book not found with id: " + id
        ));
    }

    public BookDTO addBook(BookDTO bookDTO) {

        boolean isbnExists = bookRepository.existsByIsbn(bookDTO.isbn());
        if(isbnExists){
            throw new ResourceAlreadyExistsException("Books with ISBN " + bookDTO.isbn() + " already exists");
        }

        Book book = new Book(bookDTO);
        for (Author author : book.getAuthors()) {
            Optional<Author> existingAuthor = authorRepository.findByFirstNameAndLastName(
                    author.getFirstName(),
                    author.getLastName());

            existingAuthor.ifPresent(a -> {
                book.getAuthors().remove(author);
                book.getAuthors().add(a);
            });
        }

        for (Genre genre : book.getGenres()) {
            Optional<Genre> existingGenre = genreRepository.findByName(
                    genre.getName());

            existingGenre.ifPresent(g -> {
                book.getGenres().remove(genre);
                book.getGenres().add(g);
            });
        }


        return new BookDTO(bookRepository.save(book));
    }

    public BookDTO addBookPostDTO(BookPostDTO bookDTO) {

        boolean isbnExists = bookRepository.existsByIsbn(bookDTO.isbn());
        if(isbnExists){
            throw new ResourceAlreadyExistsException("Books with ISBN " + bookDTO.isbn() + " already exists");
        }

        Book book = new Book(bookDTO);
        for (Integer authorId : bookDTO.authorIds()) {
            Author existingAuthor = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + authorId));
            book.getAuthors().add(existingAuthor);
        }

        for (Integer genreId : bookDTO.genreIds()) {
            Genre existingGenre = genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + genreId));
            book.getGenres().add(existingGenre);
        }

        return new BookDTO(bookRepository.save(book));
    }

    public BookDTO updateBook(int id, BookPostDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        book.setName(bookDTO.name());
        book.setIsbn(bookDTO.isbn());
        book.setAvailableCopies(bookDTO.availableCopies());
        book.setTotalCopies(bookDTO.totalCopies());

        book.getAuthors().clear();
        for (Integer authorId : bookDTO.authorIds()) {
            Author existingAuthor = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + authorId));
            book.getAuthors().add(existingAuthor);
        }

        book.getGenres().clear();
        for (Integer genreId : bookDTO.genreIds()) {
            Genre existingGenre = genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + genreId));
            book.getGenres().add(existingGenre);
        }

        return new BookDTO(bookRepository.save(book));
    }

    public void deleteBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceAlreadyExistsException(
                "Book not found with id: " + id
        ));
        bookRepository.delete(book);
    }


    public Page<BookDTO> getBookBySearch(String search, Integer genreId, Integer authorId, Integer page, Integer size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);


        Page<Book> all;
        if (page == null || size == null ) {
            all =  bookRepository.findBySearch(search, genreId, authorId, Pageable.unpaged(sort));
        }
        else{
            Pageable pageable = PageRequest.of(page, size, sort);
            all = bookRepository.findBySearch(search, genreId, authorId,pageable);
        }
        return all.map(BookDTO::new);
    }
}
