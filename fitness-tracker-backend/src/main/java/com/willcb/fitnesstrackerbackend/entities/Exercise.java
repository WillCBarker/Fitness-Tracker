package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="distance")
    private Double distance;

    @Column(name="duration")
    private Integer duration;

    @Column(name="reps")
    private Integer reps;

    @Column(name="sets")
    private Integer sets;

    @Column(name="weight")
    private Double weight;

    @Column(name="exercise_type")
    private String exercise_type;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    public Exercise() {
    }

    public Exercise(String name, Double distance, Integer duration, Integer reps, Integer sets, Double weight,
            String exercise_type) {
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.exercise_type = exercise_type;
    }

    // Getters and Setters
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getExercise_type() {
        return exercise_type;
    }

    public void setExercise_type(String exercise_type) {
        this.exercise_type = exercise_type;
    }

    @Override
    public String toString() {
        return "Exercise [id=" + id + ", name=" + name + ", distance=" + distance + ", duration=" + duration + ", reps="
                + reps + ", sets=" + sets + ", weight=" + weight + ", exercise_type=" + exercise_type + "]";
    }
}
