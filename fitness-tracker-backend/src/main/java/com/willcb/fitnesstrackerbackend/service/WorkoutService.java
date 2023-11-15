package com.willcb.fitnesstrackerbackend.service;

import com.willcb.fitnesstrackerbackend.controller.dto.ExerciseDetailsDTO;
import com.willcb.fitnesstrackerbackend.model.Exercise;
import com.willcb.fitnesstrackerbackend.model.Workout;
import com.willcb.fitnesstrackerbackend.repository.WorkoutRepository;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository WorkoutRepository;

    
    private void validateWorkout(Workout workout) {
        // Validate workout input
        if (workout == null || workout.getLabel() == null || workout.getExercises() == null) {
            throw new IllegalArgumentException("Workout details are incomplete");
        }
    }

    public List<ExerciseDetailsDTO> getExerciseDetailsByWorkoutID(Long workoutID) {
        Workout workout = WorkoutRepository.findById(workoutID).orElseThrow(() -> new NoSuchElementException("Workout not found"));
    
        List<Exercise> exercises = workout.getExercises();
    
        List<ExerciseDetailsDTO> exerciseDetailsList = new ArrayList<>();
        for (Exercise exercise : exercises) {
            ExerciseDetailsDTO exerciseDetailsDTO = new ExerciseDetailsDTO(exercise);
            exerciseDetailsList.add(exerciseDetailsDTO);
        }
    
        return exerciseDetailsList;
    }

    public Workout getWorkoutByID(Long workoutID){
        return WorkoutRepository.findById(workoutID).orElse(null);
    }

    public List<Workout> getAllWorkouts() {
        return WorkoutRepository.findAll();
    }

    public Workout createWorkout(Workout workout) {
        validateWorkout(workout);

        return WorkoutRepository.save(workout);
    }

    public Workout updateWorkout(Long id, Workout updatedWorkout) {
        validateWorkout(updatedWorkout);

        Workout existingWorkout = WorkoutRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Workout not found"));

        return WorkoutRepository.save(existingWorkout);
    }

    public boolean deleteWorkout(Long id) {
        if (WorkoutRepository.existsById(id)) {
            WorkoutRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
