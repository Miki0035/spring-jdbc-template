package com.mikiyas.springjackson;

import com.mikiyas.springjackson.domain.dto.AuthorDto;
import com.mikiyas.springjackson.domain.dto.BookDto;

import java.util.List;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static AuthorDto createAuthor() {
        return AuthorDto.builder().name("Author 1").age(23).build();
    }

    public static List<AuthorDto> createMultipleAuthors() {
        return List.of(
                AuthorDto.builder().name("Author 1").age(23).build(),
                AuthorDto.builder().name("Author 2").age(35).build(),
                AuthorDto.builder().name("Author 2").age(29).build()
        );
    }

    public static BookDto createTestBook(final AuthorDto author) {
        return BookDto.builder().isbn("978-2-6542-65433-0").title("Book 1").author(author).build();
    }


    public static List<BookDto> createMultipleBooks(final AuthorDto author) {
        return List.of(
                BookDto.builder().isbn("978-2-6542-65433-0").title("Book 1").author(author).build(),
                BookDto.builder().isbn("978-2-6542-65433-1").title("Book 2").author(author).build(),
                BookDto.builder().isbn("978-2-6542-65433-2").title("Book 3").author(author).build()
        );
    }
}
