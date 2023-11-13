package com.willcb.fitnesstrackerbackend.repository;

import com.willcb.fitnesstrackerbackend.model.ResistanceExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResistanceExerciseRepository extends JpaRepository<ResistanceExercise, Long> {
    // put queries here
}

