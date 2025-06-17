package com.mikiyas.springjackson;

import com.mikiyas.springjackson.domain.entities.AuthorEntity;
import com.mikiyas.springjackson.domain.entities.BookEntity;

import java.util.List;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static AuthorEntity createAuthor() {
        return AuthorEntity.builder().name("Author 1").age(23).build();
    }

    public static List<AuthorEntity> createMultipleAuthors() {
        return List.of(
                AuthorEntity.builder().name("Author 1").age(23).build(),
                AuthorEntity.builder().name("Author 2").age(35).build(),
                AuthorEntity.builder().name("Author 2").age(29).build()
        );
    }

    public static BookEntity createTestBook(final AuthorEntity author) {
        return BookEntity.builder().isbn("978-2-6542-65433-0").title("Book 1").author(author).build();
    }


    public static List<BookEntity> createMultipleBooks(final AuthorEntity author) {
        return List.of(
                BookEntity.builder().isbn("978-2-6542-65433-0").title("Book 1").author(author).build(),
                BookEntity.builder().isbn("978-2-6542-65433-1").title("Book 2").author(author).build(),
                BookEntity.builder().isbn("978-2-6542-65433-2").title("Book 3").author(author).build()
        );
    }
}
