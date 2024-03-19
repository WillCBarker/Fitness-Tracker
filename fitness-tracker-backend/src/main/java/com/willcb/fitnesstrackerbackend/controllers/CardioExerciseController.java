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

import com.willcb.fitnesstrackerbackend.entities.CardioExercise;
import com.willcb.fitnesstrackerbackend.services.CardioExerciseService;

@RestController
@RequestMapping("/exercises/cardio")
public class CardioExerciseController {

    private final CardioExerciseService cardioExerciseService;

    @Autowired
    public CardioExerciseController(CardioExerciseService cardioExerciseService) {
        this.cardioExerciseService = cardioExerciseService;
    }

    /**
     * Retrieves all cardio exercises.
     * 
     * @return List<CardioExercise> The list of all cardio exercises.
     */
    @GetMapping
    public List<CardioExercise> getAllCardioExercises() {
        return cardioExerciseService.getAllCardioExercises();
    }

    /**
     * Retrieves a cardio exercise by its ID.
     * 
     * @param id The ID of the cardio exercise to retrieve.
     * @return ResponseEntity<CardioExercise> The ResponseEntity containing the cardio exercise if found,
     *         a bad request response if the provided ID is invalid,
     *         or a not found response if the cardio exercise with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CardioExercise> getCardioExerciseByID(@PathVariable Long id) {

        try {
            CardioExercise cardioExercise = cardioExerciseService.getCardioExerciseByID(id);
            return ResponseEntity.ok(cardioExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new cardio exercise.
     * 
     * @param cardioExercise The cardio exercise object to be created.
     * @return ResponseEntity<CardioExercise> The ResponseEntity containing the created cardio exercise if successful,
     *         or a bad request response if the provided data is invalid.
     */
    @PostMapping
    public ResponseEntity<CardioExercise> createCardioExercise(@RequestBody CardioExercise cardioExercise) {

        try {
            CardioExercise createdCardioExercise = cardioExerciseService.createCardioExercise(cardioExercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCardioExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Updates an existing cardio exercise with the specified ID.
     * 
     * @param id The ID of the cardio exercise to be updated.
     * @param updatedCardioExercise The updated information of the cardio exercise.
     * @return ResponseEntity<CardioExercise> The ResponseEntity containing the updated cardio exercise if successful,
     *         a bad request response if the provided data is invalid,
     *         or a not found response if the cardio exercise with the specified ID does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CardioExercise> updateCardioExercise(@PathVariable Long id, @RequestBody CardioExercise updatedCardioExercise) {

        try {
            CardioExercise cardioExercise = cardioExerciseService.updateCardioExercise(id, updatedCardioExercise);
            return ResponseEntity.ok(cardioExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing cardio exercise with the specified ID.
     * 
     * @param id The ID of the cardio exercise to be deleted.
     * @return ResponseEntity<Void> The ResponseEntity indicating success if the cardio exercise is deleted,
     *         or a not found response if the cardio exercise with the specified ID does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardioExercise(@PathVariable Long id) {
        boolean deleted = cardioExerciseService.deleteCardioExercise(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

