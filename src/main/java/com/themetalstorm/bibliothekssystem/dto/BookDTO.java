package com.themetalstorm.bibliothekssystem.dto;

import com.themetalstorm.bibliothekssystem.model.Book;

import java.util.Set;
import java.util.stream.Collectors;

public record BookDTO(String name, String isbn, String publisher, String genre, Set<AuthorDTO> authors) {
    public BookDTO(Book book){
        this(book.getName(), book.getIsbn(), book.getPublisher(), book.getGenre(), book.getAuthors().stream().map(AuthorDTO::new).collect(Collectors.toSet()));
    }
}



