package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import com.themetalstorm.bibliothekssystem.model.Author;
import com.themetalstorm.bibliothekssystem.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }
    public void saveAuthor(AuthorDTO author) {
        authorRepository.save(new Author(author));
    }

    public void saveAllAuthors(Collection<AuthorDTO> authors) {
        List<Author> list = authors.stream().map(Author::new).toList();
        authorRepository.saveAll(list);
    }

    public void deleteAll() {
        authorRepository.deleteAll();
    }
}
