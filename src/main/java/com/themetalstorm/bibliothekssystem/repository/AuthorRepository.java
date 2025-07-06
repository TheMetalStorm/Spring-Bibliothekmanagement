package com.themetalstorm.bibliothekssystem.repository;

import com.themetalstorm.bibliothekssystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByBooks_Id(Long bookId);

    boolean existsByFirstNameAndLastName(String s, String s1);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
