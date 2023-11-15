package com.willcb.fitnesstrackerbackend.service;

import com.willcb.fitnesstrackerbackend.model.WorkoutPlan;
import com.willcb.fitnesstrackerbackend.repository.WorkoutPlanRepository;
import com.willcb.fitnesstrackerbackend.model.User;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPlanService {

    @Autowired
    private WorkoutPlanRepository WorkoutPlanRepository;


     private void validateWorkoutPlan(WorkoutPlan workoutPlan) {
        // Validate workoutPlan input
        if (workoutPlan == null || workoutPlan.getUser() == null || workoutPlan.getName() == null) {
            throw new IllegalArgumentException("WorkoutPlan details are incomplete");
        }
    }

    public List<WorkoutPlan> getWorkoutPlanByUser(User user) {
        return WorkoutPlanRepository.findByUser(user);
    }

    public WorkoutPlan getWorkoutPlanByID(Long workoutPlanID){
        return WorkoutPlanRepository.findById(workoutPlanID).orElse(null);
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return WorkoutPlanRepository.findAll();
    }

    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        validateWorkoutPlan(workoutPlan);

        return WorkoutPlanRepository.save(workoutPlan);
    }

    public WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan updatedWorkoutPlan) {
        validateWorkoutPlan(updatedWorkoutPlan);

        WorkoutPlan existingWorkoutPlan = WorkoutPlanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("WorkoutPlan not found"));

        return WorkoutPlanRepository.save(existingWorkoutPlan);
    }

    public boolean deleteWorkoutPlan(Long id) {
        if (WorkoutPlanRepository.existsById(id)) {
            WorkoutPlanRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
