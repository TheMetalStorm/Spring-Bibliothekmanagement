package com.themetalstorm.bibliothekssystem.model;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
        this.biography = authorDTO.biographym();
        this.pictureURL = authorDTO.pictureURL();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(birthDate, author.birthDate) && Objects.equals(biography, author.biography) && Objects.equals(pictureURL, author.pictureURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, biography, pictureURL);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", biography='" + biography + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }
}
