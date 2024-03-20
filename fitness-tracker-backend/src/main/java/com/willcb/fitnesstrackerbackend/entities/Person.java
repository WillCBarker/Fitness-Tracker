package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="weight")
    private double weight;

    @Column(name="height")
    private double height;

    @Column(name="gender")
    private String gender;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private WorkoutPlan workoutPlan;

    public Person() {
    }

    public Person(String name, int age, Double height, Double weight, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
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

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + ", height=" + height
                + ", gender=" + gender + ", workoutPlan=" + workoutPlan + "]";
    }
}