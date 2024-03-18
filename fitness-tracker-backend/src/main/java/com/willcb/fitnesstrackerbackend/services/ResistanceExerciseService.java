package com.willcb.fitnesstrackerbackend.services;

import com.willcb.fitnesstrackerbackend.entities.ResistanceExercise;
import com.willcb.fitnesstrackerbackend.repositories.ResistanceExerciseRepository;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResistanceExerciseService {

    public final ResistanceExerciseRepository resistanceExerciseRepository;
    
    @Autowired
    public ResistanceExerciseService(ResistanceExerciseRepository resistanceExerciseRepository) {
        this.resistanceExerciseRepository = resistanceExerciseRepository;
    }

    private void validateResistanceExercise(ResistanceExercise resistanceExercise) {
        // Validate resistanceExercise input
        if (resistanceExercise == null || resistanceExercise.getSets() <= 0 || resistanceExercise.getWeight() <= 0 || resistanceExercise.getReps() <= 0 || resistanceExercise.getName() == null) {
            throw new IllegalArgumentException("ResistanceExercise details are incomplete");
        }
    }

    public ResistanceExercise getResistanceExerciseByID(Long resistanceExerciseID) {
        return this.resistanceExerciseRepository.findById(resistanceExerciseID).orElse(null);
    }

    public List<ResistanceExercise> getAllResistanceExercises() {
        return this.resistanceExerciseRepository.findAll();
    }

    public ResistanceExercise createResistanceExercise(ResistanceExercise resistanceExercise) {
        validateResistanceExercise(resistanceExercise);

        return this.resistanceExerciseRepository.save(resistanceExercise);
    }

    public ResistanceExercise updateResistanceExercise(Long id, ResistanceExercise updatedResistanceExercise) {
        validateResistanceExercise(updatedResistanceExercise);
        ResistanceExercise existingResistanceExercise = this.resistanceExerciseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ResistanceExercise not found"));
        return this.resistanceExerciseRepository.save(existingResistanceExercise);
    }

    public boolean deleteResistanceExercise(Long id) {
        if (this.resistanceExerciseRepository.existsById(id)) {
            this.resistanceExerciseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
