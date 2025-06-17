package com.mikiyas.springjackson.controllers;

import com.mikiyas.springjackson.domain.dto.AuthorDto;
import com.mikiyas.springjackson.domain.entities.AuthorEntity;
import com.mikiyas.springjackson.mappers.impl.AuthorMapperImpl;
import com.mikiyas.springjackson.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapperImpl authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapperImpl authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);

    }

    @GetMapping
    public List<AuthorDto> listAuthors() {
        List<AuthorEntity> authors = authorService.findAll();
        return authors.stream().map(
                authorMapper::mapTo
        ).collect(Collectors.toList());
    }
}
