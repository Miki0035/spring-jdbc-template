package com.mikiyas.springjackson.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikiyas.springjackson.TestDataUtil;
import com.mikiyas.springjackson.domain.dto.AuthorDto;
import com.mikiyas.springjackson.domain.dto.BookDto;
import com.mikiyas.springjackson.domain.entities.AuthorEntity;
import com.mikiyas.springjackson.domain.entities.BookEntity;
import com.mikiyas.springjackson.mappers.impl.BookMapperImpl;
import com.mikiyas.springjackson.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.print.Book;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BookMapperImpl bookMapper;
    private final BookServiceImpl bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookMapperImpl bookMapper, BookServiceImpl bookService) {
        this.mockMvc = mockMvc;
        this.bookMapper = bookMapper;
        this.bookService = bookService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookReturnsHttpStatusCreated() throws Exception {
        BookDto book = TestDataUtil.createTestBook(null);
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/books/" + book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookReturnsCreatedBook() throws Exception {
        BookDto book = TestDataUtil.createTestBook(null);
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/books/" + book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn())
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        );
    }

    @Test
    public void testThatListBooksReturnsSHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }


    @Test
    public void testThatListBooksReturnsListOfBooks() throws Exception {

        BookDto bookDto = TestDataUtil.createTestBook(null);
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].title").value(bookDto.getTitle())

        );

    }


    @Test
    public void testThatGetBookReturnsSHttpStatus200WhenBookExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books/1234-5678-23-1212")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void testThatGetBookReturnsHttpStatus404WhenNoBookExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

    }

    @Test
    public void testThatGetBookReturnBookWhenBookExists() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/books/1234-5678-23-1212")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("1234-5678-23-1212")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Book 1")

        );

    }

    @Test
    public void testThatFullUpdateBookReturnHttpStatus200() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBook(null);
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        bookService.createUpdateBook(bookDto.getIsbn(), bookEntity);

        BookDto updatedBookDto = TestDataUtil.createMultipleBooks(null).get(0);
        String updatedBookDtoJson = objectMapper.writeValueAsString(updatedBookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBookDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void testThatUpdateBookReturnUpdatedBook() throws Exception {
        BookDto book = TestDataUtil.createTestBook(null);
        BookEntity bookEntity = bookMapper.mapFrom(book);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        book.setTitle("UPDATED");
        String bookJson = objectMapper.writeValueAsString(book);


        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/books/" + book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn())
        );

    }

}