package com.jamesg.Database2Application.services;

import com.jamesg.Database2Application.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();
}
