package com.mikiyas.springjackson.services;

import com.mikiyas.springjackson.domain.entities.BookEntity;

import java.util.List;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> findAll();
}
