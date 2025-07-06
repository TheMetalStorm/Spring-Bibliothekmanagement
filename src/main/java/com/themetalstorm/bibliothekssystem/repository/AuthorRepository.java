package com.themetalstorm.bibliothekssystem.repository;

import com.themetalstorm.bibliothekssystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByBooks_Id(Long bookId);
}
