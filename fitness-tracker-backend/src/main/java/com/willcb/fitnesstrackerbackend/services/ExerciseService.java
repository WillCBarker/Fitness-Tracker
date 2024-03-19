package com.willcb.fitnesstrackerbackend.services;

import com.willcb.fitnesstrackerbackend.entities.Exercise;
import com.willcb.fitnesstrackerbackend.repositories.ExerciseRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    private void validateExercise(Exercise exercise) {
        // Validate exercise input
        if (exercise == null || exercise.getName() == null) {
            throw new IllegalArgumentException("Exercise details are incomplete");
        }
    }

    public Exercise getExerciseByID(Long exerciseID){
        return this.exerciseRepository.findById(exerciseID)
                .orElseThrow(() -> new NoSuchElementException("Exercise with ID " + exerciseID + " not found"));
    }

    public List<Exercise> getAllExercises() {
        return this.exerciseRepository.findAll();
    }

    public Exercise createExercise(Exercise exercise) {
        validateExercise(exercise);

        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        validateExercise(updatedExercise);

        Exercise existingExercise = this.exerciseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Exercise not found"));

        return this.exerciseRepository.save(existingExercise);
    }

    public boolean deleteExercise(Long id) {
        if (this.exerciseRepository.existsById(id)) {
            this.exerciseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
