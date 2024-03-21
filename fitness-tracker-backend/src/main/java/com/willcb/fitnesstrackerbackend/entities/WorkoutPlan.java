package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "workout_plan")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(nullable = false, name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

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

    public int getId() {
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

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDayWorkoutMapping(Map<DayOfWeek, Workout> dayWorkoutMapping) {
        this.dayWorkoutMapping = dayWorkoutMapping;
    }

    @Override
    public String toString() {
        return "WorkoutPlan [id=" + id + ", name=" + name + ", person=" + person + ", dayWorkoutMapping="
                + dayWorkoutMapping + "]";
    }
}


