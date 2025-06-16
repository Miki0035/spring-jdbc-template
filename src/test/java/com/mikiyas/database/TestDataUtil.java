package com.mikiyas.database;

import com.mikiyas.database.domain.Author;
import com.mikiyas.database.domain.Book;

import java.util.List;

public final class TestDataUtil {

    private TestDataUtil() {
    }

    public static Author createAuthor() {
        return Author.builder().name("Author 1").age(23).build();
    }

    public static List<Author> createMultipleAuthors() {
        return List.of(
                Author.builder().name("Author 1").age(23).build(),
                Author.builder().name("Author 2").age(35).build(),
                Author.builder().name("Author 2").age(29).build()
        );
    }

    public static Book createTestBook(final Author author) {
        return Book.builder().isbn("978-2-6542-65433-0").title("Book 1").author(author).build();
    }


    public static List<Book> createMultipleBooks(final Author author) {
        return List.of(
                Book.builder().isbn("978-2-6542-65433-0").title("Book 1").author(author).build(),
                Book.builder().isbn("978-2-6542-65433-1").title("Book 2").author(author).build(),
                Book.builder().isbn("978-2-6542-65433-2").title("Book 3").author(author).build()
        );
    }
}
