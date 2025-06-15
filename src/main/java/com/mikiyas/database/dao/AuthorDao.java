package com.mikiyas.database.dao;

import com.mikiyas.database.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    void create(Author author);

    Optional<Author> findOne(Long authorId);

    List<Author> find();

    void update(Long id, Author author);

    void delete(Long authorId);
}
