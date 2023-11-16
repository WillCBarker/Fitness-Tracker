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

    @Autowired
    private CardioExerciseService cardioExerciseService;

    @GetMapping
    public List<CardioExercise> getAllCardioExercises() {
        // need service method
        return cardioExerciseService.getAllCardioExercises();
    }

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


    @PostMapping
    public ResponseEntity<CardioExercise> createCardioExercise(@RequestBody CardioExercise cardioExercise) {

        try {
            CardioExercise createdCardioExercise = cardioExerciseService.createCardioExercise(cardioExercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCardioExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

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
