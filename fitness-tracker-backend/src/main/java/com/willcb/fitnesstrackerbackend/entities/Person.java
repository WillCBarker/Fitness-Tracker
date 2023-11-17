package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private double weight;
    private double height;
    private String gender;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private WorkoutPlan workoutPlan;

    public Person() {
    }

    public Person(String name, Integer age, Double height, Double weight, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    // Getters

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getHeight() {
        return this.height;
    }

    public String getGender() {
        return this.gender;
    }

    public WorkoutPlan getWorkoutPlan() {
        return this.workoutPlan;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
        
        if (workoutPlan != null) {
            workoutPlan.setPerson(this);
        }
    }
}
