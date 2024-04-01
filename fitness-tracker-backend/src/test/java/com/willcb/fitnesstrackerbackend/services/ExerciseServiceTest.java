package com.willcb.fitnesstrackerbackend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.willcb.fitnesstrackerbackend.entities.Exercise;
import com.willcb.fitnesstrackerbackend.repositories.ExerciseRepository;

public class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;
    private ExerciseService exerciseService;
    AutoCloseable autoCloseable;
    Exercise exercise;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        exerciseService = new ExerciseService(exerciseRepository);
        exercise = new Exercise("Test Exercise", 1.0, 20, 2, 3, null, "cardio");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateExercise() {
      when(exerciseRepository.save(exercise)).thenReturn(exercise);
    
      Exercise createdExercise = exerciseService.createExercise(exercise);
    
      assertThat(createdExercise).isEqualTo(exercise);
      verify(exerciseRepository).save(exercise);
    }

    @Test
    void testDeleteExercise_Exists() {
        Long exerciseId = 1L;
      
        when(exerciseRepository.existsById(exerciseId)).thenReturn(true);
      
        exerciseService.deleteExercise(exerciseId);
      
        verify(exerciseRepository).existsById(exerciseId);
        verify(exerciseRepository).deleteById(exerciseId);
    }

    @Test
        void testGetAllExercises() {
        List<Exercise> exercises = Arrays.asList(new Exercise(), new Exercise());
        when(exerciseRepository.findAll()).thenReturn(exercises);

        List<Exercise> retrievedExercises = exerciseService.getAllExercises();

        verify(exerciseRepository).findAll();

        assertThat(retrievedExercises).isEqualTo(exercises);
    }

    @Test
    void testGetExerciseByID() {
        Long exerciseId = 1L;

        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(exercise)); // Optional with Exercise object

        // Call the service method
        Exercise retrievedExercise = exerciseService.getExerciseByID(exerciseId);

        // Verify interaction with repository
        verify(exerciseRepository).findById(exerciseId);

        // Verify retrieved exercise matches expected
        assertThat(retrievedExercise).isEqualTo(exercise);
    }

    @Test
    void testUpdateExercise() {
        Long exerciseId = 1L;
        Exercise existingExercise = new Exercise("Test Exercise", 1.0, 20, 2, 3, null, "cardio");
        Exercise updatedExercise = new Exercise("Test Updated Exercise ", 1.0, 20, 2, 3, null, "cardio");
        // Mock exerciseRepository (assuming findById and save are called)
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(existingExercise));
        when(exerciseRepository.save(updatedExercise)).thenReturn(updatedExercise);

        // Call the service method
        Exercise updated = exerciseService.updateExercise(exerciseId, updatedExercise);

        // Verify interaction with repository
        verify(exerciseRepository).findById(exerciseId);
        verify(exerciseRepository).save(updatedExercise);

        // Verify returned exercise matches updated exercise
        assertThat(updated).isEqualTo(updatedExercise);
    }
}
