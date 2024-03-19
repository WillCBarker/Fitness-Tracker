package com.willcb.fitnesstrackerbackend.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.willcb.fitnesstrackerbackend.dto.ExerciseDetailsDTO;
import com.willcb.fitnesstrackerbackend.entities.Workout;
import com.willcb.fitnesstrackerbackend.services.WorkoutService;


@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /**
     * Retrieves all workouts.
     * 
     * @return List<Workout> The list of all workouts.
     */
    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    /**
     * Retrieves a workout by its ID.
     * 
     * @param id The ID of the workout to retrieve.
     * @return ResponseEntity<Workout> The ResponseEntity containing the workout if found,
     *         a bad request response if the provided ID is invalid,
     *         or a not found response if the workout with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutByID(@PathVariable Long id) {

        try {
            Workout workout = workoutService.getWorkoutByID(id);
            return ResponseEntity.ok(workout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves exercises for a workout by its ID.
     * 
     * @param id The ID of the workout to retrieve exercises for.
     * @return ResponseEntity<List<ExerciseDetailsDTO>> The ResponseEntity containing the list of exercise details if found,
     *         or a not found response if the workout with the specified ID does not exist or has no exercises.
     */
    @GetMapping("/{id}/exercises")
    public ResponseEntity<List<ExerciseDetailsDTO>> getExercisesByWorkoutId(@PathVariable Long id) {
        try {
            List<ExerciseDetailsDTO> exerciseDetailsList = workoutService.getExerciseDetailsByWorkoutID(id);

            if (!exerciseDetailsList.isEmpty()) {
                return ResponseEntity.ok(exerciseDetailsList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new workout.
     * 
     * @param workout The workout object to be created.
     * @return ResponseEntity<Workout> The ResponseEntity containing the created workout if successful,
     *         or a bad request response if the provided data is invalid.
     */
    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {

        try {
            Workout createdWorkout = workoutService.createWorkout(workout);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Updates an existing workout with the specified ID.
     * 
     * @param id The ID of the workout to be updated.
     * @param updatedWorkout The updated information of the workout.
     * @return ResponseEntity<Workout> The ResponseEntity containing the updated workout if successful,
     *         a bad request response if the provided data is invalid,
     *         or a not found response if the workout with the specified ID does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout) {

        try {
            Workout workout = workoutService.updateWorkout(id, updatedWorkout);
            return ResponseEntity.ok(workout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing workout with the specified ID.
     * 
     * @param id The ID of the workout to be deleted.
     * @return ResponseEntity<Void> The ResponseEntity indicating success if the workout is deleted,
     *         or a not found response if the workout with the specified ID does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        boolean deleted = workoutService.deleteWorkout(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

