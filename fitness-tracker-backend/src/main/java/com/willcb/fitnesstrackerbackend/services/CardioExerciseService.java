package com.willcb.fitnesstrackerbackend.services;

import com.willcb.fitnesstrackerbackend.entities.CardioExercise;
import com.willcb.fitnesstrackerbackend.repositories.CardioExerciseRepository;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardioExerciseService {

    @Autowired
    private CardioExerciseRepository CardioExerciseRepository;

    private void validateCardioExercise(CardioExercise cardioExercise) {
        // Validate cardioExercise input
        if (cardioExercise == null || cardioExercise.getDuration() <= 0 || cardioExercise.getDistance() <= 0 || cardioExercise.getName() == null) {
            throw new IllegalArgumentException("CardioExercise details are incomplete");
        }
    }

    public CardioExercise getCardioExerciseByID(Long cardioExerciseID) {
        return CardioExerciseRepository.findById(cardioExerciseID).orElse(null);
    }

    public List<CardioExercise> getAllCardioExercises() {
        return CardioExerciseRepository.findAll();
    }

    public CardioExercise createCardioExercise(CardioExercise cardioExercise) {
        validateCardioExercise(cardioExercise);

        return CardioExerciseRepository.save(cardioExercise);
    }

    public CardioExercise updateCardioExercise(Long id, CardioExercise updatedCardioExercise) {
        validateCardioExercise(updatedCardioExercise);
        CardioExercise existingCardioExercise = CardioExerciseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("CardioExercise not found"));
        return CardioExerciseRepository.save(existingCardioExercise);
    }

    public boolean deleteCardioExercise(Long id) {
        if (CardioExerciseRepository.existsById(id)) {
            CardioExerciseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}