package com.jamesg.Database2Application.dao.impl;
// Unit test for AuthorDaoImpl

import com.jamesg.Database2Application.TestDataUtil;
import com.jamesg.Database2Application.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // Enable Mockito for this test class
public class AuthorDaoImplTests {


    // Before each test is run, a new instance of the class under test is created
    // and the mocked JdbcTemplate is injected into it.
    @Mock
    private JdbcTemplate jdbcTemplate; // Mocked JdbcTemplate for database operations

    @InjectMocks
    private AuthorDaoImpl underTest; // Instance of the class under test

    // Test method to make sure the correct SQL is generated when creating an author
    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {

        // Create an Author object to be used in the test
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        // Mockito verifies that the correct SQL statement is executed with the expected parameters
        // This also protects against SQL injection attacks
        // Test will only pass if the SQL string and parameters match exactly
        verify(jdbcTemplate).update(
                // Mockito requires matches so use eq() to match the SQL string
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Abigail Rose"), eq(80)
        );
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(), // when ArgumentMatcher matches the AuthorRowMapper
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql() {
        underTest.find(); // Returns all results from the database
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.update(author.getId(), author);
        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                1L, "Abigail Rose", 80, 1L
        );
    }

    @Test
    public void testThatDeleteGeneratesTheCorrectSql() {
        underTest.delete(1L);
        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id = ?",
                1L
        );
    }
}
