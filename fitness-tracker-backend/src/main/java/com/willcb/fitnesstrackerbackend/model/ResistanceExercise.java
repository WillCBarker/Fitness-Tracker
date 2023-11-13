package com.willcb.fitnesstrackerbackend.model;

import javax.persistence.*;


@Entity
@DiscriminatorValue("RESISTANCE")
public class ResistanceExercise extends Exercise {

    private int sets;
    private int reps;
    private double weight;

    public ResistanceExercise() {
    }

    // Getters

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    // Setters

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}

