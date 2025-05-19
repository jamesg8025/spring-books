package com.jamesg.Database2Application.repositories;


import com.jamesg.Database2Application.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String>,
        PagingAndSortingRepository<BookEntity, String> { // String is the type of the ID (ISBN) for the Book entity
    // No need to implement any methods, as Spring Data JPA provides the implementation for us
    // We can add custom query methods if needed, but for basic CRUD operations, this is sufficient


    // Introducing Pagination (also extends PagingAndSortingRepository)


}
