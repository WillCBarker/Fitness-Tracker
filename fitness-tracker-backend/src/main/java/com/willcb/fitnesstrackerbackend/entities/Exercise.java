package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

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
    private String exerciseType;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    public Exercise() {
    }

    public Exercise(String name, Double distance, Integer duration, Integer reps, Integer sets, Double weight,
            String exerciseType) {
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.exerciseType = exerciseType;
    }

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exercise_type) {
        this.exerciseType = exercise_type;
    }

    @Override
    public String toString() {
        return "Exercise [id=" + id + ", name=" + name + ", distance=" + distance + ", duration=" + duration + ", reps="
                + reps + ", sets=" + sets + ", weight=" + weight + ", exercise_type=" + exerciseType + "]";
    }
}
