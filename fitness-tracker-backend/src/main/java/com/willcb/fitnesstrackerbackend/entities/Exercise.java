package com.willcb.fitnesstrackerbackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exercise_type", discriminatorType = DiscriminatorType.STRING)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "workout_id")  // Adjust the column name if needed
    private Workout workout;


    private String name;

    public Exercise() {
    }

    public Exercise(String name) {
        this.name = name;
    }

    // Getters

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }
}
