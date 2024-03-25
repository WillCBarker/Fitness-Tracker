package com.willcb.fitnesstrackerbackend.repositories;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.willcb.fitnesstrackerbackend.entities.Exercise;
import com.willcb.fitnesstrackerbackend.entities.Person;
import com.willcb.fitnesstrackerbackend.entities.Workout;
import com.willcb.fitnesstrackerbackend.entities.WorkoutPlan;

@DataJpaTest
public class WorkoutRepositoryTest {

    @Autowired
    private WorkoutRepository workoutRepository;

    @BeforeEach
    public void setUp() {
        Workout workout = createTestWorkout();
        workoutRepository.save(workout);
    }

    @AfterEach
    public void tearDown() {
        workoutRepository.deleteAll();
    }

    private Workout createTestWorkout() {
        List<Exercise> exercises = Arrays.asList(
            new Exercise("Test Cardio 1", 1.0, 20, 2, 3, null, "cardio"),
            new Exercise("Test Cardio 2", 2.0, 30, 1, 4, null, "cardio")
        );
        return new Workout(null, "Test Workout", exercises);
    }
}
