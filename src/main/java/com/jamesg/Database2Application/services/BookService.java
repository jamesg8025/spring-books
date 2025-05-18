package com.jamesg.Database2Application.services;

import com.jamesg.Database2Application.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    // Method to create a new book
    BookEntity createUpdateBook(String isbn, BookEntity book);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);
}
