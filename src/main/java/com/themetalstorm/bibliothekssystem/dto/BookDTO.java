package com.themetalstorm.bibliothekssystem.dto;

import com.themetalstorm.bibliothekssystem.model.Book;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;
import java.util.stream.Collectors;

public record BookDTO(String name, String isbn, String publisher, Set<GenreDTO> genres, Set<AuthorDTO> authors, @DefaultValue(value = "1") int availableCopies, @DefaultValue(value = "1") int totalCopies) {
    public BookDTO(Book book){
        this(book.getName(), book.getIsbn(), book.getPublisher(),
                book.getGenres().stream().map(GenreDTO::new).collect(Collectors.toSet()),
                book.getAuthors().stream().map(AuthorDTO::new).collect(Collectors.toSet()), book.getAvailableCopies(), book.getTotalCopies());
    }
}



