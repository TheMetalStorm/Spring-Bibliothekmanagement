package com.themetalstorm.bibliothekssystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "book_id", nullable = false)
    private Integer bookId;
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;
    @Column(name = "returned")
    private LocalDateTime returned;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Loan(Integer bookId, Integer userId, LoanStatus status) {
        this.bookId = bookId;
        this.userId = userId;
        this.status = status;
    }
}
