package com.willcb.fitnesstrackerbackend.services;

import com.willcb.fitnesstrackerbackend.entities.CardioExercise;
import com.willcb.fitnesstrackerbackend.repositories.CardioExerciseRepository;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardioExerciseService {

    private CardioExerciseRepository cardioExerciseRepository;

    @Autowired
    public CardioExerciseService(CardioExerciseRepository cardioExerciseRepository) {
        this.cardioExerciseRepository = cardioExerciseRepository;
    }

    private void validateCardioExercise(CardioExercise cardioExercise) {
        // Validate cardioExercise input
        if (cardioExercise == null || cardioExercise.getDuration() <= 0 || cardioExercise.getDistance() <= 0 || cardioExercise.getName() == null) {
            throw new IllegalArgumentException("CardioExercise details are incomplete");
        }
    }

    public CardioExercise getCardioExerciseByID(Long cardioExerciseID) {
        return this.cardioExerciseRepository.findById(cardioExerciseID).orElse(null);
    }

    public List<CardioExercise> getAllCardioExercises() {
        return this.cardioExerciseRepository.findAll();
    }

    public CardioExercise createCardioExercise(CardioExercise cardioExercise) {
        validateCardioExercise(cardioExercise);

        return this.cardioExerciseRepository.save(cardioExercise);
    }

    public CardioExercise updateCardioExercise(Long id, CardioExercise updatedCardioExercise) {
        validateCardioExercise(updatedCardioExercise);
        CardioExercise existingCardioExercise = this.cardioExerciseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("CardioExercise not found"));
        return this.cardioExerciseRepository.save(existingCardioExercise);
    }

    public boolean deleteCardioExercise(Long id) {
        if (this.cardioExerciseRepository.existsById(id)) {
            this.cardioExerciseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}