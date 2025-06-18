package com.mikiyas.springjackson.controllers;

import com.mikiyas.springjackson.domain.dto.BookDto;
import com.mikiyas.springjackson.domain.entities.BookEntity;
import com.mikiyas.springjackson.mappers.impl.BookMapperImpl;
import com.mikiyas.springjackson.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto book) {
        BookEntity bookEntity = bookMapper.mapFrom(book);
        boolean bookExists = bookService.isExists(isbn);
        BookEntity savedBook = bookService.createUpdateBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);

        if (bookExists) {
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }


    }

    @GetMapping
    public List<BookDto> listBooks() {
        List<BookEntity> books = bookService.findAll();
        return books.stream().map(
                bookMapper::mapTo
        ).collect(Collectors.toList());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> bookEntity = bookService.findOne(isbn);
        return bookEntity.map(book -> {
            BookDto bookDto = bookMapper.mapTo(book);
            return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}
