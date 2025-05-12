package com.jamesg.Database2Application.services.impl;

import com.jamesg.Database2Application.domain.entities.BookEntity;
import com.jamesg.Database2Application.repositories.BookRepository;
import com.jamesg.Database2Application.services.BookService;
import org.springframework.stereotype.Service;

@Service // Denotes this class as a bean
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {

        // Make sure that the isbn on the bookEntity object is the same as the isbn provided in the method
        book.setIsbn(isbn);

        return bookRepository.save(book); // Save the book entity to the database
    }
}
