package com.jamesg.Database2Application.services.impl;

import com.jamesg.Database2Application.domain.entities.AuthorEntity;
import com.jamesg.Database2Application.repositories.AuthorRepository;
import com.jamesg.Database2Application.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service // To be able to inject this class, makes this class a Spring bean
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
       return authorRepository.save(authorEntity); // returns an author entity because that's JPA's default return type
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository
                .findAll()
                .spliterator(),
                false)
                .collect(Collectors.toList());
    }
}
