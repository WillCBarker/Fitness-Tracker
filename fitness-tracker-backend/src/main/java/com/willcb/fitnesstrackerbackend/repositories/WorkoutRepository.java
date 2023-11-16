package com.willcb.fitnesstrackerbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willcb.fitnesstrackerbackend.entities.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    // put queries here
}

