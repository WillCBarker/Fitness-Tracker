package com.willcb.fitnesstrackerbackend.model;

import javax.persistence.*;
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

    // Getters

    public Long getId() {
        return id;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public String getLabel() {
        return label;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

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