package com.mikiyas.database.repositories;

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
public class AuthorRepositoryIntegrationTest {

    private final AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRead() {
        Author author = TestDataUtil.createAuthor();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRead() {
        List<Author> authors = TestDataUtil.createMultipleAuthors();
        for (Author author : authors)
            underTest.save(author);

        Iterable<Author> results = underTest.findAll();
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
        underTest.save(author);
        author.setName("UPDATED");

        underTest.save(author);
        Optional<Author> results = underTest.findById(author.getId());

        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.createAuthor();
        underTest.save(author);

        underTest.deleteById(author.getId());

        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isEmpty();

    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        List<Author> authors = TestDataUtil.createMultipleAuthors();
        for (Author author : authors)
            underTest.save(author);

        Iterable<Author> result = underTest.ageLessThan(30);

        assertThat(result).containsExactly(
                authors.get(0),
                authors.get(1)
        );

    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        List<Author> authors = TestDataUtil.createMultipleAuthors();
        for (Author author : authors)
            underTest.save(author);

        Iterable<Author> result = underTest.ageGreaterThan(30);

        assertThat(result).containsExactly(
                authors.get(1)
        );
    }

}