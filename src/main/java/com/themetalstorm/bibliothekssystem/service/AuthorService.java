package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import com.themetalstorm.bibliothekssystem.exceptions.ResourceAlreadyExistsException;
import com.themetalstorm.bibliothekssystem.exceptions.ResourceNotFoundException;
import com.themetalstorm.bibliothekssystem.model.Author;
import com.themetalstorm.bibliothekssystem.repository.AuthorRepository;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }
    public AuthorDTO saveAuthor(AuthorDTO author) {

        if(authorRepository.existsByFirstNameAndLastName(author.firstName(), author.lastName())){
            throw new ResourceAlreadyExistsException("Author already exists");
        }
        return new AuthorDTO(authorRepository.save(new Author(author)));
    }

    public void saveAllAuthors(Collection<AuthorDTO> authors) {
        List<Author> list = authors.stream().filter(authorDTO -> !authorRepository.existsByFirstNameAndLastName(authorDTO.firstName(), authorDTO.lastName())).map(Author::new).toList();
        authorRepository.saveAll(list);
    }

    public void deleteAll() {
        authorRepository.deleteAll();
    }

    public AuthorDTO getAuthorById(int id) {
        return authorRepository.findById(id).map(AuthorDTO::new).orElseThrow(() -> new ResourceNotFoundException(
                "Author not found with id: " + id
        ));
    }

    public Page<AuthorDTO> getAllAuthors(Integer page, Integer size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);

        Page<Author> all;
        if(page == null || size == null ) {
            all =  authorRepository.findAll(Pageable.unpaged(sort));
        }
        else{
            Pageable pageable = PageRequest.of(page, size, sort);
            all = authorRepository.findAll(pageable);
        }
        return all.map(AuthorDTO::new);
    }

    public AuthorDTO updateAuthor(int id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        author.setFirstName(authorDTO.firstName());
        author.setLastName(authorDTO.lastName());
        author.setBiography(authorDTO.biography());
        author.setBirthDate(authorDTO.birthDate());
        author.setPictureURL(authorDTO.pictureURL());
        return new AuthorDTO(authorRepository.save(author));
    }

    @Transactional
    public void deleteById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceAlreadyExistsException("Author not found with id: " + id));
        authorRepository.delete(author);
    }
}
