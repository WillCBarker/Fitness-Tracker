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

import com.willcb.fitnesstrackerbackend.entities.Exercise;
import com.willcb.fitnesstrackerbackend.services.ExerciseService;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    
    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    /**
     * Retrieves all exercises.
     * 
     * @return List<Exercise> The list of all exercises.
     */
    @GetMapping
    public List<Exercise> getAllExercises() {
        // need service method
        return exerciseService.getAllExercises();
    }

    /**
     * Retrieves an exercise by its ID.
     * 
     * @param id The ID of the exercise to retrieve.
     * @return ResponseEntity<Exercise> The ResponseEntity containing the exercise if found,
     *         a bad request response if the provided ID is invalid,
     *         or a not found response if the exercise with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseByID(@PathVariable Long id) {

        try {
            Exercise exercise = exerciseService.getExerciseByID(id);
            return ResponseEntity.ok(exercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new exercise.
     * 
     * @param exercise The exercise object to be created.
     * @return ResponseEntity<Exercise> The ResponseEntity containing the created exercise if successful,
     *         or a bad request response if the provided data is invalid.
     */
    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {

        try {
            Exercise createdExercise = exerciseService.createExercise(exercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Updates an existing exercise with the specified ID.
     * 
     * @param id The ID of the exercise to be updated.
     * @param updatedExercise The updated information of the exercise.
     * @return ResponseEntity<Exercise> The ResponseEntity containing the updated exercise if successful,
     *         a bad request response if the provided data is invalid,
     *         or a not found response if the exercise with the specified ID does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise updatedExercise) {

        try {
            Exercise exercise = exerciseService.updateExercise(id, updatedExercise);
            return ResponseEntity.ok(exercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing exercise with the specified ID.
     * 
     * @param id The ID of the exercise to be deleted.
     * @return ResponseEntity<Void> The ResponseEntity indicating success if the exercise is deleted,
     *         or a not found response if the exercise with the specified ID does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        boolean deleted = exerciseService.deleteExercise(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

