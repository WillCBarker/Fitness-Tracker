package com.willcb.fitnesstrackerbackend.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willcb.fitnesstrackerbackend.entities.Exercise;
import com.willcb.fitnesstrackerbackend.repositories.ExerciseRepository;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    private void validateExercise(Exercise exercise) {
        // Validate exercise input
        if (exercise == null || exercise.getName() == null || exercise.getExerciseType() == null) {
            throw new IllegalArgumentException("Exercise details are incomplete");
        }
    }

    public Exercise getExerciseByID(Long exerciseID){
        return exerciseRepository.findById(exerciseID)
                .orElseThrow(() -> new NoSuchElementException("Exercise with ID " + exerciseID + " not found"));
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise createExercise(Exercise exercise) {
        validateExercise(exercise);

        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Long id, Exercise updatedExercise) {
        validateExercise(updatedExercise);

        Exercise existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Exercise not found"));
        updatedExercise.setId(existingExercise.getId());
        
        return exerciseRepository.save(updatedExercise);
    }

    public boolean deleteExercise(Long id) {
        if (exerciseRepository.existsById(id)) {
            exerciseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
