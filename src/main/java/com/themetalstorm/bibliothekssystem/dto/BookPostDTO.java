package com.themetalstorm.bibliothekssystem.dto;

import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;

public record BookPostDTO (String name, String isbn, String publisher, Set<Integer> genreIds, Set<Integer> authorIds, @DefaultValue(value = "1") int availableCopies, @DefaultValue(value = "1") int totalCopies){

}
