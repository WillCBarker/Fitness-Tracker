package com.willcb.fitnesstrackerbackend.dto;

import java.time.DayOfWeek;

import com.willcb.fitnesstrackerbackend.entities.Workout;

public class WorkoutDetailsDTO {

    private DayOfWeek dayOfWeek;
    private Workout workout;

    public WorkoutDetailsDTO(DayOfWeek dayOfWeek, Workout workout) {
        this.dayOfWeek = dayOfWeek;
        this.workout = workout;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
