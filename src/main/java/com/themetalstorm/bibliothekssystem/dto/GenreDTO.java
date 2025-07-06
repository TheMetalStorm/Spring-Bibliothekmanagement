package com.themetalstorm.bibliothekssystem.dto;

import com.themetalstorm.bibliothekssystem.model.Genre;

public record GenreDTO (String name, String description)  {
    public GenreDTO(Genre genre){
        this(genre.getName(), genre.getDescription());
    }
}
