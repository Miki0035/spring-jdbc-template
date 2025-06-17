package com.mikiyas.springjackson.controllers;

import com.mikiyas.springjackson.domain.dto.BookDto;
import com.mikiyas.springjackson.domain.entities.BookEntity;
import com.mikiyas.springjackson.mappers.impl.BookMapperImpl;
import com.mikiyas.springjackson.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookMapperImpl bookMapper;
    private final BookService bookService;

    public BookController(BookMapperImpl bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book) {
        BookEntity bookEntity = bookMapper.mapFrom(book);
        BookEntity savedBook = bookService.createBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);

    }

    @GetMapping
    public List<BookDto> listBooks() {
        List<BookEntity> books = bookService.findAll();
        return books.stream().map(
                bookMapper::mapTo
        ).collect(Collectors.toList());
    }
}
