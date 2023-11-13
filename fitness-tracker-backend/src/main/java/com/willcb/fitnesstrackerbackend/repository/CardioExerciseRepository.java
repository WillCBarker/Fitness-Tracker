package com.willcb.fitnesstrackerbackend.repository;

import com.willcb.fitnesstrackerbackend.model.CardioExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardioExerciseRepository extends JpaRepository<CardioExercise, Long> {
    // put queries here
}

