package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RESISTANCE")
public class ResistanceExercise extends Exercise {

    private int sets;
    private int reps;
    private double weight;

    public ResistanceExercise() {
    }

    public ResistanceExercise(int sets, int reps, double weight) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }
    

    // Getters

    public int getSets() {
        return this.sets;
    }

    public int getReps() {
        return this.reps;
    }

    public double getWeight() {
        return this.weight;
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

