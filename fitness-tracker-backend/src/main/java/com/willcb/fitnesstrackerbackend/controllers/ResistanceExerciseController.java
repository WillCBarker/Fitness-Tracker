package com.willcb.fitnesstrackerbackend.controllers;

import org.springframework.web.bind.annotation.*;

import com.willcb.fitnesstrackerbackend.entities.ResistanceExercise;
import com.willcb.fitnesstrackerbackend.services.ResistanceExerciseService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/exercises/resistance")
public class ResistanceExerciseController {

    private final ResistanceExerciseService resistanceExerciseService;

    @Autowired
    public ResistanceExerciseController(ResistanceExerciseService resistanceExerciseService) {
        this.resistanceExerciseService = resistanceExerciseService;
    }

    /**
     * Retrieves all resistance exercises.
     * 
     * @return List<ResistanceExercise> The list of all resistance exercises.
     */
    @GetMapping
    public List<ResistanceExercise> getAllResistanceExercises() {
        return resistanceExerciseService.getAllResistanceExercises();
    }

    /**
     * Retrieves a resistance exercise by its ID.
     * 
     * @param id The ID of the resistance exercise to retrieve.
     * @return ResponseEntity<ResistanceExercise> The ResponseEntity containing the resistance exercise if found,
     *         a bad request response if the provided ID is invalid,
     *         or a not found response if the resistance exercise with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResistanceExercise> getResistanceExerciseByID(@PathVariable Long id) {

        try {
            ResistanceExercise resistanceExercise = resistanceExerciseService.getResistanceExerciseByID(id);
            if (resistanceExercise != null) {
                return ResponseEntity.ok(resistanceExercise);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Creates a new resistance exercise.
     * 
     * @param resistanceExercise The resistance exercise object to be created.
     * @return ResponseEntity<ResistanceExercise> The ResponseEntity containing the created resistance exercise if successful,
     *         or a bad request response if the provided data is invalid.
     */
    @PostMapping
    public ResponseEntity<ResistanceExercise> createResistanceExercise(@RequestBody ResistanceExercise resistanceExercise) {

        try {
            ResistanceExercise createdResistanceExercise = resistanceExerciseService.createResistanceExercise(resistanceExercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResistanceExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Updates an existing resistance exercise with the specified ID.
     * 
     * @param id The ID of the resistance exercise to be updated.
     * @param updatedResistanceExercise The updated information of the resistance exercise.
     * @return ResponseEntity<ResistanceExercise> The ResponseEntity containing the updated resistance exercise if successful,
     *         a bad request response if the provided data is invalid,
     *         or a not found response if the resistance exercise with the specified ID does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResistanceExercise> updateResistanceExercise(@PathVariable Long id, @RequestBody ResistanceExercise updatedResistanceExercise) {

        try {
            ResistanceExercise resistanceExercise = resistanceExerciseService.updateResistanceExercise(id, updatedResistanceExercise);
            return ResponseEntity.ok(resistanceExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing resistance exercise with the specified ID.
     * 
     * @param id The ID of the resistance exercise to be deleted.
     * @return ResponseEntity<Void> The ResponseEntity indicating success if the resistance exercise is deleted,
     *         or a not found response if the resistance exercise with the specified ID does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResistanceExercise(@PathVariable Long id) {
        boolean deleted = resistanceExerciseService.deleteResistanceExercise(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

 