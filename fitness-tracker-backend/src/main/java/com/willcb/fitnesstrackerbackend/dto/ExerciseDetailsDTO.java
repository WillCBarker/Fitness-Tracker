package com.willcb.fitnesstrackerbackend.dto;


import com.willcb.fitnesstrackerbackend.model.Exercise;

public class ExerciseDetailsDTO {
    
    private Exercise exercise;

    public ExerciseDetailsDTO(Exercise exercise) {
        this.exercise = exercise;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
