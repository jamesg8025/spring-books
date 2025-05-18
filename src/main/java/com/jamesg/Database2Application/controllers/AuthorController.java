package com.jamesg.Database2Application.controllers;

import com.jamesg.Database2Application.domain.dto.AuthorDto;
import com.jamesg.Database2Application.domain.entities.AuthorEntity;
import com.jamesg.Database2Application.mappers.Mapper;
import com.jamesg.Database2Application.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // Endpoint for read many
    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors() {
        List<AuthorEntity> authors = authorService.findAll();
        return authors.stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList()); // Returns EVERY author in the database
    }

    @GetMapping(path = "/authors/{id}") // whatever is in {id} will be passed to the method as a parameter
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        // In the case that an author exists, we get an optional with val of the author. if not, we get an empty optional
        Optional<AuthorEntity> foundAuthor = authorService.findOne(id);
        return foundAuthor.map(authorEntity ->
        {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
