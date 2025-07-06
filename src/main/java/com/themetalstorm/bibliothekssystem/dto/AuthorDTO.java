package com.themetalstorm.bibliothekssystem.dto;

import com.themetalstorm.bibliothekssystem.model.Author;

import java.time.LocalDate;

public record AuthorDTO(String firstName, String lastName, LocalDate birthDate, String biography, String pictureURL) {

    public AuthorDTO(Author author) {
        this(author.getFirstName(), author.getLastName(), author.getBirthDate(), author.getBiography(), author.getPictureURL());
    }
}
