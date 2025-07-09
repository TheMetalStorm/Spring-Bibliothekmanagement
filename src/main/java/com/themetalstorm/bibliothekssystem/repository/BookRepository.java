package com.themetalstorm.bibliothekssystem.repository;

import com.themetalstorm.bibliothekssystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthors_Id(int authorId);

    List<Book> findByGenres_Id(int genreId);

    @Query("SELECT DISTINCT b FROM Book b " +
            "JOIN b.genres g " +
            "JOIN b.authors a " +
            "WHERE " +
            "(:search IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:genreId IS NULL OR g.id = :genreId) AND " +
            "(:authorId IS NULL OR a.id = :authorId) " )
    List<Book> findBySearch(@Param("search") String search,
                            @Param("genreId") Integer genreId,
                            @Param("authorId") Integer authorId);


    boolean existsByIsbn(String isbn);
}
