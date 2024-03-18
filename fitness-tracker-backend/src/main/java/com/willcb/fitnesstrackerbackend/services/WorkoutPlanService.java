package com.willcb.fitnesstrackerbackend.services;

import com.willcb.fitnesstrackerbackend.repositories.WorkoutPlanRepository;
import com.willcb.fitnesstrackerbackend.dto.WorkoutDetailsDTO;
import com.willcb.fitnesstrackerbackend.entities.Person;
import com.willcb.fitnesstrackerbackend.entities.Workout;
import com.willcb.fitnesstrackerbackend.entities.WorkoutPlan;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.*;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    private void validateWorkoutPlan(WorkoutPlan workoutPlan) {
        // Validate workoutPlan input
        if (workoutPlan == null || workoutPlan.getPerson() == null || workoutPlan.getName() == null) {
            throw new IllegalArgumentException("WorkoutPlan details are incomplete");
        }
    }

    public List<WorkoutDetailsDTO> getWorkoutsDetailsByPlanId(Long workoutPlanID) {
        WorkoutPlan workoutPlan = this.workoutPlanRepository.findById(workoutPlanID).orElseThrow(() -> new NoSuchElementException("WorkoutPlan not found"));

        Map<DayOfWeek, Workout> dayWorkoutMapping = workoutPlan.getDayWorkoutMapping();

        // Convert the map entries to WorkoutDetailsDTO for response
        List<WorkoutDetailsDTO> workoutDetailsList = new ArrayList<>();
        for (Map.Entry<DayOfWeek, Workout> entry : dayWorkoutMapping.entrySet()) {
            DayOfWeek dayOfWeek = entry.getKey();
            Workout workout = entry.getValue();

            WorkoutDetailsDTO workoutDetailsDTO = new WorkoutDetailsDTO(dayOfWeek, workout);
            workoutDetailsList.add(workoutDetailsDTO);
        }

        return workoutDetailsList;
    }

    public List<WorkoutPlan> getWorkoutPlanByPerson(Person person) {
        return this.workoutPlanRepository.findByPerson(person);
    }

    public WorkoutPlan getWorkoutPlanByID(Long workoutPlanID){
        return this.workoutPlanRepository.findById(workoutPlanID).orElse(null);
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return this.workoutPlanRepository.findAll();
    }

    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        validateWorkoutPlan(workoutPlan);

        return this.workoutPlanRepository.save(workoutPlan);
    }

    public WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan updatedWorkoutPlan) {
        validateWorkoutPlan(updatedWorkoutPlan);

        WorkoutPlan existingWorkoutPlan = this.workoutPlanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("WorkoutPlan not found"));

        return this.workoutPlanRepository.save(existingWorkoutPlan);
    }

    public boolean deleteWorkoutPlan(Long id) {
        if (this.workoutPlanRepository.existsById(id)) {
            this.workoutPlanRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
