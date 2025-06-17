package com.mikiyas.springjackson.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikiyas.springjackson.TestDataUtil;
import com.mikiyas.springjackson.domain.dto.AuthorDto;
import com.mikiyas.springjackson.domain.entities.AuthorEntity;
import com.mikiyas.springjackson.mappers.impl.AuthorMapperImpl;
import com.mikiyas.springjackson.services.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final AuthorServiceImpl authorService;
    private final AuthorMapperImpl authorMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorServiceImpl authorService, AuthorMapperImpl authorMapper) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
        this.authorMapper = authorMapper;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorDto author = TestDataUtil.createAuthor();
        String authorJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorDto author = TestDataUtil.createAuthor();
        String authorJson = objectMapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Author 1")

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(23)
        );

    }

    @Test
    public void testThatListAuthorsReturnsSHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {

        AuthorDto authorDto = TestDataUtil.createAuthor();
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        authorService.createAuthor(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Author 1")

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(23)
        );

    }

}
