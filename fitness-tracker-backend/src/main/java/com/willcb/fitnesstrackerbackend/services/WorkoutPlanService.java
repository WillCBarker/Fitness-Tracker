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

    @Autowired
    private WorkoutPlanRepository WorkoutPlanRepository;


    private void validateWorkoutPlan(WorkoutPlan workoutPlan) {
        // Validate workoutPlan input
        if (workoutPlan == null || workoutPlan.getPerson() == null || workoutPlan.getName() == null) {
            throw new IllegalArgumentException("WorkoutPlan details are incomplete");
        }
    }

    public List<WorkoutDetailsDTO> getWorkoutsDetailsByPlanId(Long workoutPlanID) {
        WorkoutPlan workoutPlan = WorkoutPlanRepository.findById(workoutPlanID).orElseThrow(() -> new NoSuchElementException("WorkoutPlan not found"));

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
        return WorkoutPlanRepository.findByPerson(person);
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
