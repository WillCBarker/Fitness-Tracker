package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CARDIO")
public class CardioExercise extends Exercise {

    private int duration;
    private double distance;

    public CardioExercise() {
    }

    public CardioExercise(int duration, double distance) {
        this.duration = duration;
        this.distance = distance;
    }
    
    // Getters

    public int getDuration() {
        return this.duration;
    }

    public double getDistance() {
        return this.distance;
    }

    // Setters

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

