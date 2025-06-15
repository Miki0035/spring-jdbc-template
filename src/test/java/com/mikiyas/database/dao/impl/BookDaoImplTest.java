package com.mikiyas.database.dao.impl;

import com.mikiyas.database.TestDataUtil;
import com.mikiyas.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSQL() {
        Book book = TestDataUtil.createTestBook();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)"),
                eq("978-2-6542-65433-0"),
                eq("Book 1"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneOneBookGeneratesTheCorrectSQL() {
        underTest.findOne("978-2-6542-65433-0");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ?  LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("978-2-6542-65433-0")
        );
    }

    @Test
    public void testThatFindOneManyGeneratesTheCorrectSQL() {
        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSQL() {
        Book book = TestDataUtil.createTestBook();
        underTest.update("978-2-6542-65433-0", book);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ? ",
                "978-2-6542-65433-0", "Book 1", 1L, "978-2-6542-65433-0"
        );

    }

    @Test
    public void testThatDeleteGeneratesTheCorrectSQL() {
        underTest.delete("978-2-6542-65433-0");

        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                "978-2-6542-65433-0"
        );

    }
}
