package com.themetalstorm.bibliothekssystem.dto;

import com.themetalstorm.bibliothekssystem.model.Genre;

public record GenreDTO (Integer id, String name, String description)  {
    public GenreDTO(Genre genre){
        this(genre.getId(), genre.getName(), genre.getDescription());
    }
}
