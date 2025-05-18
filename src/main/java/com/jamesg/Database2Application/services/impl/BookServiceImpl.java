package com.jamesg.Database2Application.services.impl;

import com.jamesg.Database2Application.domain.entities.BookEntity;
import com.jamesg.Database2Application.repositories.BookRepository;
import com.jamesg.Database2Application.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport
                .stream(
                        bookRepository.findAll().spliterator(),
                false)
                .collect(Collectors.toList());
    }
}
