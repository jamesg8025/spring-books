package com.jamesg.Database2Application.dao;

import com.jamesg.Database2Application.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    void create(Author author);

    // In the case that an author is found with the id provided, return it wrapped in an Optional
    // if author is not found, return an empty Optional
    // doing this is more typesafe than returning null
    Optional<Author> findOne(long l);

    List<Author> find();

    // Explicitly id the argument to this method, which allows for changing of id's
    void update(long id, Author author);

    void delete(long l);
}
