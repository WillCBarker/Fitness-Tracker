package com.willcb.fitnesstrackerbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willcb.fitnesstrackerbackend.entities.CardioExercise;

@Repository
public interface CardioExerciseRepository extends JpaRepository<CardioExercise, Long> {
    // put queries here
}

