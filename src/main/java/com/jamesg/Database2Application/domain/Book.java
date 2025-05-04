package com.jamesg.Database2Application.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    @Id // No auto-generated ID for books, as they are identified by their ISBN
    private String isbn;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL) // Establishes a many-to-one relationship with Author
    @JoinColumn(name = "author_id")
    private Author author; // Changed to Author object instead of authorId to establish a relationship

}
