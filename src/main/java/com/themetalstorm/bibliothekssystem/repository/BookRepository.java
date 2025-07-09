package com.themetalstorm.bibliothekssystem.repository;

import com.themetalstorm.bibliothekssystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthors_Id(int authorId);

    List<Book> findByGenres_Id(int genreId);

    @Query("SELECT b FROM Book b " +
            "JOIN b.genres g " +
            "JOIN b.authors a " +
            "WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :book, '%')) " +
            "AND LOWER(g.name) LIKE LOWER(CONCAT('%', :genre, '%')) " +
            "AND (LOWER(CONCAT(a.firstName, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :author, '%')) " +
            "OR LOWER(CONCAT(a.lastName, ' ', a.firstName)) LIKE LOWER(CONCAT('%', :author, '%')))")
    List<Book> findByTitle(String book, String genre, String author);
    boolean existsByIsbn(String isbn);
}
