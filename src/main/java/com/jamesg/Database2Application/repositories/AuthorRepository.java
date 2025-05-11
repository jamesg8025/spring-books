package com.jamesg.Database2Application.repositories;

// Spring JPA provides the interface for us, so we don't need to implement it - if we extend the correct repository interface


import com.jamesg.Database2Application.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // Similar to @Component, makes this a Spring bean
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int age); // Custom query method to find authors younger than a certain age


    @Query("SELECT a from AuthorEntity a WHERE a.age > ?1") // Custom query to find authors older than a certain age
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}
