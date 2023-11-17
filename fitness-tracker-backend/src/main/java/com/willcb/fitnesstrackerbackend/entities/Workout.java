package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private WorkoutPlan workoutPlan;

    private String label;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    public Workout() {
    }

    public Workout(WorkoutPlan workoutPlan, String label, List<Exercise> exercises) {
        this.workoutPlan = workoutPlan;
        this.label = label;
        this.exercises = exercises;
    }

    // Getters

    public Long getId() {
        return this.id;
    }

    public WorkoutPlan getWorkoutPlan() {
        return this.workoutPlan;
    }

    public String getLabel() {
        return this.label;
    }

    public List<Exercise> getExercises() {
        return this.exercises;
    }

    // Setters

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}