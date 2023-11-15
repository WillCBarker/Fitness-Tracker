package com.willcb.fitnesstrackerbackend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.willcb.fitnesstrackerbackend.model.WorkoutPlan;
import com.willcb.fitnesstrackerbackend.service.WorkoutPlanService;


@RestController
@RequestMapping("/workoutplans")
public class WorkoutPlanController {
        
    @Autowired
    private WorkoutPlanService WorkoutPlanService;

    @GetMapping
    public List<WorkoutPlan> getAllWorkoutPlans() {
        // need service method
        return WorkoutPlanService.getAllWorkoutPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanByID(@PathVariable Long id) {

        try {
            WorkoutPlan workoutPlan = WorkoutPlanService.getWorkoutPlanByID(id);
            return ResponseEntity.ok(workoutPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<WorkoutPlan> createWorkoutPlan(@RequestBody WorkoutPlan workoutPlan) {

        try {
            WorkoutPlan createdWorkoutPlan = WorkoutPlanService.createWorkoutPlan(workoutPlan);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlan> updateWorkoutPlan(@PathVariable Long id, @RequestBody WorkoutPlan updatedWorkoutPlan) {

        try {
            WorkoutPlan workoutPlan = WorkoutPlanService.updateWorkoutPlan(id, updatedWorkoutPlan);
            return ResponseEntity.ok(workoutPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        boolean deleted = WorkoutPlanService.deleteWorkoutPlan(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}