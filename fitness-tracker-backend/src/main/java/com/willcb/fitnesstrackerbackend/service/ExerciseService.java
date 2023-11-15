package com.willcb.fitnesstrackerbackend.service;

import com.willcb.fitnesstrackerbackend.model.Exercise;
import com.willcb.fitnesstrackerbackend.repository.ExerciseRepository;

import java.util.*;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository ExerciseRepository;

        private void validateExercise(Exercise exercise) {
        // Validate exercise input
        if (exercise == null || exercise.getName() == null) {
            throw new IllegalArgumentException("Exercise details are incomplete");
        }
    }

    public Exercise getExerciseByID(Long exerciseID){
        return ExerciseRepository.findById(exerciseID).orElse(null);
    }

    public List<Exercise> getAllExercises() {
        return ExerciseRepository.findAll();
    }

    public Exercise createExercise(Exercise exercise) {
        validateExercise(exercise);

        return ExerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        validateExercise(updatedExercise);

        Exercise existingExercise = ExerciseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Exercise not found"));

        return ExerciseRepository.save(existingExercise);
    }

    public boolean deleteExercise(Long id) {
        if (ExerciseRepository.existsById(id)) {
            ExerciseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
