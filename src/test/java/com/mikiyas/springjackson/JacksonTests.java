package com.mikiyas.springjackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikiyas.springjackson.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JacksonTests {

//    @Test
//    public void testThatObjectMapperCanCreateJsonFromObject() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        BookEntity bookEntity = BookEntity.builder()
//                .isbn("123-4567-8901")
//                .author("Author 1")
//                .title("Book 1")
//                .yearPublished("2000")
//                .build();
//
//        String result = objectMapper.writeValueAsString(bookEntity);
//
//        assertThat(result).isEqualTo("{\"isbn\":\"123-4567-8901\",\"title\":\"Book 1\",\"author\":\"Author 1\",\"yearPublished\":\"2000\"}");
//    }
//
//    @Test
//    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        BookEntity bookEntity = BookEntity.builder()
//                .isbn("123-4567-8901")
//                .author("Author 1")
//                .title("Book 1")
//                .yearPublished("2000")
//                .build();
//
//
//        String json ="{\"isbn\":\"123-4567-8901\",\"title\":\"Book 1\",\"author\":\"Author 1\",\"yearPublished\":\"2000\"}";
//        BookEntity result = objectMapper.readValue(json, BookEntity.class);
//
//        assertThat(result).isEqualTo(bookEntity);
//    }
}
