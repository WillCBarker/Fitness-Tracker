package com.willcb.fitnesstrackerbackend.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.willcb.fitnesstrackerbackend.dto.ExerciseDetailsDTO;
import com.willcb.fitnesstrackerbackend.entities.Workout;
import com.willcb.fitnesstrackerbackend.services.WorkoutService;


@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public List<Workout> getAllWorkouts() {
        // need service method
        return workoutService.getAllWorkouts();
    }

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

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {

        try {
            Workout createdWorkout = workoutService.createWorkout(workout);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

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
