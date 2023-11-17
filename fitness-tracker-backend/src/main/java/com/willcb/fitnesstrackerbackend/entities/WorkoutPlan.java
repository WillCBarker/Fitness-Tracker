package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "workout_plan")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(nullable = false)
    private String name;

    // Map each day of the week to a collection of exercises (a Workout)
    @ElementCollection
    @CollectionTable(name = "day_workout_mapping", joinColumns = @JoinColumn(name = "plan_id"))
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DayOfWeek, Workout> dayWorkoutMapping;

    public WorkoutPlan() {
    }

    public WorkoutPlan(Person person, String name) {
        this.person = person;
        this.name = name;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public String getName() {
        return name;
    }

    public Map<DayOfWeek, Workout> getDayWorkoutMapping() {
        return dayWorkoutMapping;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDayWorkoutMapping(Map<DayOfWeek, Workout> dayWorkoutMapping) {
        this.dayWorkoutMapping = dayWorkoutMapping;
    }

}


