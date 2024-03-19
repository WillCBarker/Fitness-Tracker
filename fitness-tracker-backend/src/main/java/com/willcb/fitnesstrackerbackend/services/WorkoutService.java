package com.willcb.fitnesstrackerbackend.services;

import com.willcb.fitnesstrackerbackend.dto.ExerciseDetailsDTO;
import com.willcb.fitnesstrackerbackend.entities.Exercise;
import com.willcb.fitnesstrackerbackend.entities.Workout;
import com.willcb.fitnesstrackerbackend.repositories.WorkoutRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    
    private void validateWorkout(Workout workout) {
        // Validate workout input
        if (workout == null || workout.getLabel() == null || workout.getExercises() == null) {
            throw new IllegalArgumentException("Workout details are incomplete");
        }
    }

    public List<ExerciseDetailsDTO> getExerciseDetailsByWorkoutID(Long workoutID) {
        Workout workout = this.workoutRepository.findById(workoutID).orElseThrow(() -> new NoSuchElementException("Workout with ID " + workoutID + " not found"));
    
        List<Exercise> exercises = workout.getExercises();
    
        List<ExerciseDetailsDTO> exerciseDetailsList = new ArrayList<>();
        for (Exercise exercise : exercises) {
            ExerciseDetailsDTO exerciseDetailsDTO = new ExerciseDetailsDTO(exercise);
            exerciseDetailsList.add(exerciseDetailsDTO);
        }
    
        return exerciseDetailsList;
    }

    public Workout getWorkoutByID(Long workoutID){
        return this.workoutRepository.findById(workoutID).orElse(null);
    }

    public List<Workout> getAllWorkouts() {
        return this.workoutRepository.findAll();
    }

    public Workout createWorkout(Workout workout) {
        validateWorkout(workout);

        return this.workoutRepository.save(workout);
    }

    public Workout updateWorkout(Long id, Workout updatedWorkout) {
        validateWorkout(updatedWorkout);

        Workout existingWorkout = this.workoutRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Workout not found"));

        return this.workoutRepository.save(existingWorkout);
    }

    public boolean deleteWorkout(Long id) {
        if (this.workoutRepository.existsById(id)) {
            this.workoutRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
