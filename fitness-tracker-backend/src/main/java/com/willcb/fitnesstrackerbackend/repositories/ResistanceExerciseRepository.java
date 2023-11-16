package com.willcb.fitnesstrackerbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willcb.fitnesstrackerbackend.entities.ResistanceExercise;

@Repository
public interface ResistanceExerciseRepository extends JpaRepository<ResistanceExercise, Long> {
    // put queries here
}

