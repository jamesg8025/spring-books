package com.jamesg.Database2Application.repositories;
// Name of this class is important as it is picked up by Maven Surefire Plugin
// and used to run the integration tests

import com.jamesg.Database2Application.TestDataUtil;
import com.jamesg.Database2Application.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // Starts up test version of the application context
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Cleans up the database after each test
public class AuthorEntityRepositoryIntegrationTests {

    // Reference to the AuthorDaoImpl class
    private AuthorRepository underTest;

    // Constructor to inject the AuthorDaoImpl class
    // Also need to add @Autowired to the constructor to allow Spring to inject the dependency
    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    // Test that an author is created in the database
    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        // Reuse the AuthorDaoImpl class from the unit tests
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorEntityA();
        underTest.save(authorEntity); // Create the author in the database
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent(); // Check that the author is present
        // isEqual uses the equals method in the Author class to compare the two objects
        // because Lombok is being used, every single instance variable is compared
        assertThat(result.get()).isEqualTo(authorEntity); // Check that the author is the same as the one created
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
        underTest.save(authorEntityA); // Create the author in the database
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
        underTest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();
        underTest.save(authorEntityC);

        Iterable<AuthorEntity> result = underTest.findAll();
        // Check that there are 3 authors in the database, and that they are the same as the ones created
        assertThat(result).hasSize(3).containsExactly(authorEntityA, authorEntityB, authorEntityC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
        underTest.save(authorEntityA); // Create the author in the database
        authorEntityA.setName("UPDATED");
        underTest.save(authorEntityA); // save is used for both create and update
        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isPresent(); // Check that the author is present
        assertThat(result.get()).isEqualTo(authorEntityA); // checking if result is the same as updated author name

    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorEntityA();
        underTest.save(authorEntityA);
        underTest.deleteById(authorEntityA.getId());
        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isEmpty(); // Check that the author is not present
    }

    // This tests shows that the custom query method works based on the method name
    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        underTest.save(testAuthorEntityA);
        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorEntityB);
        AuthorEntity testAuthorEntityC = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorEntityC);

        Iterable<AuthorEntity> result = underTest.ageLessThan(50);
        assertThat(result).containsExactly(testAuthorEntityB, testAuthorEntityC);
    }

    // This method name is not a name that Spring Data JPA understands, so a custom query is needed (HQL)
    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorEntityA();
        underTest.save(testAuthorEntityA);
        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorB();
        underTest.save(testAuthorEntityB);
        AuthorEntity testAuthorEntityC = TestDataUtil.createTestAuthorC();
        underTest.save(testAuthorEntityC);

        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(50);
        assertThat(result).containsExactly(testAuthorEntityA);
    }
}
