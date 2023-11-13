package com.willcb.fitnesstrackerbackend.model;

import javax.persistence.*;


@Entity
@DiscriminatorValue("CARDIO")
public class CardioExercise extends Exercise {

    private int duration;
    private double distance;

    public CardioExercise() {
    }

    // Getters

    public int getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    // Setters

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

