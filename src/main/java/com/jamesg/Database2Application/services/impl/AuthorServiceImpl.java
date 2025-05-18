package com.jamesg.Database2Application.services.impl;

import com.jamesg.Database2Application.domain.entities.AuthorEntity;
import com.jamesg.Database2Application.repositories.AuthorRepository;
import com.jamesg.Database2Application.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service // To be able to inject this class, makes this class a Spring bean
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // simply saves the author entity to the database
    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
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

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id); // simply a pass-through to the repository
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }
}
