package com.jamesg.Database2Application.services;

import com.jamesg.Database2Application.domain.entities.BookEntity;

public interface BookService {

    // Method to create a new book
    BookEntity createBook(String isbn, BookEntity book);
}
