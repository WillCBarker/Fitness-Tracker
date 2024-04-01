package com.willcb.fitnesstrackerbackend.repositories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.willcb.fitnesstrackerbackend.entities.Person;
import com.willcb.fitnesstrackerbackend.entities.WorkoutPlan;

@DataJpaTest
public class WorkoutPlanRepositoryTest {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Mock
    Person person;

    @BeforeEach
    public void setUp() {
        WorkoutPlan workoutPlan = new WorkoutPlan(person, "Test Workout Plan");
        workoutPlanRepository.save(workoutPlan);
    }

    @AfterEach
    public void tearDown() {
        workoutPlanRepository.deleteAll();
    } 
}
