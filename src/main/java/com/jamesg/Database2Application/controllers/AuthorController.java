package com.jamesg.Database2Application.controllers;

import com.jamesg.Database2Application.domain.dto.AuthorDto;
import com.jamesg.Database2Application.domain.entities.AuthorEntity;
import com.jamesg.Database2Application.mappers.Mapper;
import com.jamesg.Database2Application.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    // Inject the AuthorService bean into this controller
    private AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    // Constructor
    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    // PostMapping creates a HTTP POST endpoint
    // RequestBody converts the JSON body of the request into a Java object
    // Author is an object that represents the author, to be converted to JSON
    // ResponseEntity is a Spring class that represents the entire HTTP response
    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        //return authorMapper.mapTo(savedAuthorEntity); // Convert the saved author entity back to a DTO
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }
}
