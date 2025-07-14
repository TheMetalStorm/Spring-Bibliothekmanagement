package com.themetalstorm.bibliothekssystem.model;

import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import com.themetalstorm.bibliothekssystem.dto.BookPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String isbn;

    private String publisher;

    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @Column(nullable = false)
    private int availableCopies;
    @Column(nullable = false)
    private int totalCopies;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Book(String name, String isbn, HashSet<Author> authors, String publisher, HashSet<Genre> genres, @DefaultValue(value = "1") int availableCopies, @DefaultValue(value = "1") int totalCopies) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.genres = genres;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }
    public Book(BookDTO bookDTO) {
        this.name = bookDTO.name();
        this.isbn = bookDTO.isbn();
        this.publisher = bookDTO.publisher();
        this.genres = bookDTO.genres().stream().map(Genre::new).collect(Collectors.toCollection(HashSet::new));
        this.authors =  bookDTO.authors().stream().map(Author::new).collect(Collectors.toCollection(HashSet::new));
        this.availableCopies = bookDTO.availableCopies();
        this.totalCopies = bookDTO.totalCopies();
    }


    public Book(BookPostDTO bookDTO) {
        this.name = bookDTO.name();
        this.isbn = bookDTO.isbn();
        this.publisher = bookDTO.publisher();
        this.genres = new HashSet<>();
        this.authors =  new HashSet<>();
        this.availableCopies = bookDTO.availableCopies();
        this.totalCopies = bookDTO.totalCopies();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name) && Objects.equals(isbn, book.isbn) && Objects.equals(publisher, book.publisher) && Objects.equals(genres, book.genres) && Objects.equals(authors, book.authors) && Objects.equals(createdAt, book.createdAt) && Objects.equals(updatedAt, book.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isbn, publisher,  createdAt, updatedAt);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

}
