package com.willcb.fitnesstrackerbackend.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private ResistanceExerciseService resistanceExerciseService;

    @GetMapping
    public List<ResistanceExercise> getAllResistanceExercises() {
        // need service method
        return resistanceExerciseService.getAllResistanceExercises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResistanceExercise> getResistanceExerciseByID(@PathVariable Long id) {

        try {
            ResistanceExercise resistanceExercise = resistanceExerciseService.getResistanceExerciseByID(id);
            return ResponseEntity.ok(resistanceExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<ResistanceExercise> createResistanceExercise(@RequestBody ResistanceExercise resistanceExercise) {

        try {
            ResistanceExercise createdResistanceExercise = resistanceExerciseService.createResistanceExercise(resistanceExercise);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResistanceExercise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

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
 