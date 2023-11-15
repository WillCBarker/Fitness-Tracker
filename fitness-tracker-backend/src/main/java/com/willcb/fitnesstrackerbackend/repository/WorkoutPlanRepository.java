package com.willcb.fitnesstrackerbackend.repository;

import com.willcb.fitnesstrackerbackend.model.User;
import com.willcb.fitnesstrackerbackend.model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    List<WorkoutPlan> findByUser(User user);
}

