package com.themetalstorm.bibliothekssystem.model;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authors",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_author_first_last_name",
                        columnNames = {"firstName", "lastName"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate birthDate;
    private String biography;
    private String pictureURL;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Author(AuthorDTO authorDTO) {
        this.firstName = authorDTO.firstName();
        this.lastName = authorDTO.lastName();
        this.birthDate = authorDTO.birthDate();
        this.biography = authorDTO.biography();
        this.pictureURL = authorDTO.pictureURL();
    }
}
