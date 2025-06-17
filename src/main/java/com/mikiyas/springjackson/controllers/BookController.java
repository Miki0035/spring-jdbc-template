package com.mikiyas.springjackson.controllers;

import com.mikiyas.springjackson.domain.entities.BookEntity;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {

//    @GetMapping("/books")
//    public BookEntity getBook() {
//        return BookEntity.builder().isbn("123-4567-8901").author("Author 1").title("Book 1").yearPublished("2000").build();
//    }
//
//    @PostMapping("/books")
//    public BookEntity createBook(@RequestBody final BookEntity bookEntity) {
//        log.info("Get book" + bookEntity.toString());
//        return bookEntity;
//
//    }
}
