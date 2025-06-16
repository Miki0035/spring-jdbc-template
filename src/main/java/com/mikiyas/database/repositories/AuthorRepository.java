package com.mikiyas.database.repositories;

import com.mikiyas.database.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> ageLessThan(int age);

    Iterable<Author> ageGreaterThan(int age);
}
