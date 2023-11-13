package com.willcb.fitnesstrackerbackend.model;

import javax.persistence.*;


@Entity
@Table(name = "exercise")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exercise_type", discriminatorType = DiscriminatorType.STRING)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Exercise() {
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
