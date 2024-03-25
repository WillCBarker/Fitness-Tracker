package com.willcb.fitnesstrackerbackend.repositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.willcb.fitnesstrackerbackend.entities.Person;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        Person person = new Person("Test Man", 20, 72.0, 165.0, "M");
        personRepository.save(person);
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }
}
