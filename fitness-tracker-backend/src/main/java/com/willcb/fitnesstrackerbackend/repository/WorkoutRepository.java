package com.willcb.fitnesstrackerbackend.repository;

import com.willcb.fitnesstrackerbackend.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    // put queries here
}

