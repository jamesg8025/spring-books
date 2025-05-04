package com.jamesg.Database2Application.repositories;

// Spring JPA provides the interface for us, so we don't need to implement it - if we extend the correct repository interface


import com.jamesg.Database2Application.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // Similar to @Component, makes this a Spring bean
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
