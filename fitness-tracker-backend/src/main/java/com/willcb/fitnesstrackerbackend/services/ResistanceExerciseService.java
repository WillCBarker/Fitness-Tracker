package com.willcb.fitnesstrackerbackend.services;

import com.willcb.fitnesstrackerbackend.entities.ResistanceExercise;
import com.willcb.fitnesstrackerbackend.repositories.ResistanceExerciseRepository;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResistanceExerciseService {

    @Autowired
    private ResistanceExerciseRepository ResistanceExerciseRepository;

    private void validateResistanceExercise(ResistanceExercise resistanceExercise) {
        // Validate resistanceExercise input
        if (resistanceExercise == null || resistanceExercise.getSets() <= 0 || resistanceExercise.getWeight() <= 0 || resistanceExercise.getReps() <= 0 || resistanceExercise.getName() == null) {
            throw new IllegalArgumentException("ResistanceExercise details are incomplete");
        }
    }

    public ResistanceExercise getResistanceExerciseByID(Long resistanceExerciseID) {
        return ResistanceExerciseRepository.findById(resistanceExerciseID).orElse(null);
    }

    public List<ResistanceExercise> getAllResistanceExercises() {
        return ResistanceExerciseRepository.findAll();
    }

    public ResistanceExercise createResistanceExercise(ResistanceExercise resistanceExercise) {
        validateResistanceExercise(resistanceExercise);

        return ResistanceExerciseRepository.save(resistanceExercise);
    }

    public ResistanceExercise updateResistanceExercise(Long id, ResistanceExercise updatedResistanceExercise) {
        validateResistanceExercise(updatedResistanceExercise);
        ResistanceExercise existingResistanceExercise = ResistanceExerciseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("ResistanceExercise not found"));
        return ResistanceExerciseRepository.save(existingResistanceExercise);
    }

    public boolean deleteResistanceExercise(Long id) {
        if (ResistanceExerciseRepository.existsById(id)) {
            ResistanceExerciseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
