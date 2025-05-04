package com.jamesg.Database2Application.repositories;
// Name of this class is important as it is picked up by Maven Surefire Plugin
// and used to run the integration tests

import com.jamesg.Database2Application.TestDataUtil;
import com.jamesg.Database2Application.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // Starts up test version of the application context
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Cleans up the database after each test
public class AuthorRepositoryIntegrationTests {

    // Reference to the AuthorDaoImpl class
    private AuthorRepository underTest;

    // Constructor to inject the AuthorDaoImpl class
    // Also need to add @Autowired to the constructor to allow Spring to inject the dependency
    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    // Test that an author is created in the database
    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        // Reuse the AuthorDaoImpl class from the unit tests
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author); // Create the author in the database
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent(); // Check that the author is present
        // isEqual uses the equals method in the Author class to compare the two objects
        // because Lombok is being used, every single instance variable is compared
        assertThat(result.get()).isEqualTo(author); // Check that the author is the same as the one created
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA); // Create the author in the database
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAll();
        // Check that there are 3 authors in the database, and that they are the same as the ones created
        assertThat(result).hasSize(3).containsExactly(authorA, authorB, authorC);
    }

//    @Test
//    public void testThatAuthorCanBeUpdated() {
//        Author authorA = TestDataUtil.createTestAuthorA();
//        underTest.create(authorA); // Create the author in the database
//        authorA.setName("UPDATED");
//        underTest.update(authorA.getId(), authorA);
//        Optional<Author> result = underTest.findOne(authorA.getId());
//        assertThat(result).isPresent(); // Check that the author is present
//        assertThat(result.get()).isEqualTo(authorA);
//
//    }
//
//    @Test
//    public void testThatAuthorCanBeDeleted() {
//        Author authorA = TestDataUtil.createTestAuthorA();
//        underTest.create(authorA);
//        underTest.delete(authorA.getId());
//        Optional<Author> result = underTest.findOne(authorA.getId());
//        assertThat(result).isEmpty(); // Check that the author is not present
//    }
}
