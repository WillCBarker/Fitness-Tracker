package com.willcb.fitnesstrackerbackend.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.willcb.fitnesstrackerbackend.dto.WorkoutDetailsDTO;
import com.willcb.fitnesstrackerbackend.entities.WorkoutPlan;
import com.willcb.fitnesstrackerbackend.services.WorkoutPlanService;

@RestController
@RequestMapping("/workoutplans")
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    @Autowired
    public WorkoutPlanController(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    /**
     * Retrieves all workout plans.
     * 
     * @return List<WorkoutPlan> The list of all workout plans.
     */
    @GetMapping
    public List<WorkoutPlan> getAllWorkoutPlans() {
        return workoutPlanService.getAllWorkoutPlans();
    }

    /**
     * Retrieves a workout plan by its ID.
     * 
     * @param id The ID of the workout plan to retrieve.
     * @return ResponseEntity<WorkoutPlan> The ResponseEntity containing the workout plan if found,
     *         a bad request response if the provided ID is invalid,
     *         or a not found response if the workout plan with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanByID(@PathVariable Long id) {

        try {
            WorkoutPlan workoutPlan = workoutPlanService.getWorkoutPlanByID(id);
            return ResponseEntity.ok(workoutPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves workouts for a workout plan by its ID.
     * 
     * @param id The ID of the workout plan to retrieve workouts for.
     * @return ResponseEntity<List<WorkoutDetailsDTO>> The ResponseEntity containing the list of workout details if found,
     *         or a not found response if the workout plan with the specified ID does not exist or has no workouts.
     */
    @GetMapping("/{id}/workouts")
    public ResponseEntity<List<WorkoutDetailsDTO>> getWorkoutsByPlanId(@PathVariable Long id) {
        try {
            List<WorkoutDetailsDTO> workoutDetailsList = workoutPlanService.getWorkoutsDetailsByPlanId(id);

            if (!workoutDetailsList.isEmpty()) {
                return ResponseEntity.ok(workoutDetailsList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new workout plan.
     * 
     * @param workoutPlan The workout plan object to be created.
     * @return ResponseEntity<WorkoutPlan> The ResponseEntity containing the created workout plan if successful,
     *         or a bad request response if the provided data is invalid.
     */
    @PostMapping
    public ResponseEntity<WorkoutPlan> createWorkoutPlan(@RequestBody WorkoutPlan workoutPlan) {

        try {
            WorkoutPlan createdWorkoutPlan = workoutPlanService.createWorkoutPlan(workoutPlan);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Updates an existing workout plan with the specified ID.
     * 
     * @param id The ID of the workout plan to be updated.
     * @param updatedWorkoutPlan The updated information of the workout plan.
     * @return ResponseEntity<WorkoutPlan> The ResponseEntity containing the updated workout plan if successful,
     *         a bad request response if the provided data is invalid,
     *         or a not found response if the workout plan with the specified ID does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlan> updateWorkoutPlan(@PathVariable Long id, @RequestBody WorkoutPlan updatedWorkoutPlan) {

        try {
            WorkoutPlan workoutPlan = workoutPlanService.updateWorkoutPlan(id, updatedWorkoutPlan);
            return ResponseEntity.ok(workoutPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing workout plan with the specified ID.
     * 
     * @param id The ID of the workout plan to be deleted.
     * @return ResponseEntity<Void> The ResponseEntity indicating success if the workout plan is deleted,
     *         or a not found response if the workout plan with the specified ID does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        boolean deleted = workoutPlanService.deleteWorkoutPlan(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
