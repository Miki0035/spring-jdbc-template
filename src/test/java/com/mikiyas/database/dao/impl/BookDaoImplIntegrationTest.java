package com.mikiyas.database.dao.impl;

// TESTS CREATE , READ FUNCTIONALITY FOR BOOK

import com.mikiyas.database.TestDataUtil;
import com.mikiyas.database.dao.AuthorDao;
import com.mikiyas.database.domain.Author;
import com.mikiyas.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    private final BookDaoImpl underTest;
    private final AuthorDao authorDao;


    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRead() {
        Author author = TestDataUtil.createAuthor();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());

        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRead() {
        Author author = TestDataUtil.createAuthor();
        authorDao.create(author);
        List<Book> books = TestDataUtil.createMultipleBooks();

        for (Book book : books)
            underTest.create(book);

        List<Book> results = underTest.find();

        assertThat(results).hasSize(3)
                .containsExactly(
                        books.get(0),
                        books.get(1),
                        books.get(2)
                );
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.createAuthor();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);

        book.setTitle("UPDATED");
        underTest.update(book.getIsbn(), book);

        Optional<Book> results = underTest.findOne(book.getIsbn());
        assertThat(results).isPresent();
        assertThat(results.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author author = TestDataUtil.createAuthor();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);

        underTest.delete(book.getIsbn());

        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isEmpty();

    }

}
