package com.mikiyas.database.dao.impl;

// TESTS CREATE , READ FUNCTIONALITY FOR AUTHOR

import com.mikiyas.database.TestDataUtil;
import com.mikiyas.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

    private final AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRead() {
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRead() {
        List<Author> authors = TestDataUtil.createMultipleAuthors();
        for (Author author : authors)
            underTest.create(author);

        List<Author> results = underTest.find();
        assertThat(results).hasSize(3)
                .containsExactly(
                        authors.get(0),
                        authors.get(1),
                        authors.get(2)
                );

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);
        author.setName("UPDATEd");

        underTest.update(author.getId(), author);
        Optional<Author> results = underTest.findOne(author.getId());

        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);

        underTest.delete(author.getId());

        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isEmpty();

    }
}
