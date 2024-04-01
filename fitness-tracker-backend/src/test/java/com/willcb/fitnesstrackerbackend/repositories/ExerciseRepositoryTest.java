package com.willcb.fitnesstrackerbackend.repositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.willcb.fitnesstrackerbackend.entities.Exercise;

@DataJpaTest
public class ExerciseRepositoryTest {
    
    @Autowired
    private ExerciseRepository exerciseRepository;
    
    @BeforeEach
    void setUp() {
        Exercise exercise = new Exercise("Test Exercise", 1.0, 20, 2, 3, null, "cardio");
        exerciseRepository.save(exercise);
    }

    @AfterEach
    void tearDown() {
        exerciseRepository.deleteAll();
    }
}
