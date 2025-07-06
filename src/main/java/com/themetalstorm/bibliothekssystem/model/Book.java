package com.themetalstorm.bibliothekssystem.model;

import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(nullable = false)
    private String isbn;

    private String publisher;

    private String genre;

    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Book(String name, String isbn, HashSet<Author> authors, String publisher, String genre) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.genre = genre;
    }
    public Book(BookDTO bookDTO) {
        this.name = bookDTO.name();
        this.isbn = bookDTO.isbn();
        this.publisher = bookDTO.publisher();
        this.genre = bookDTO.genre();
        this.authors =  bookDTO.authors().stream().map(Author::new).collect(Collectors.toCollection(HashSet::new));

    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name) && Objects.equals(isbn, book.isbn) && Objects.equals(publisher, book.publisher) && Objects.equals(genre, book.genre) && Objects.equals(authors, book.authors) && Objects.equals(createdAt, book.createdAt) && Objects.equals(updatedAt, book.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isbn, authors, publisher, genre, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", authors=" + authors +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

}
