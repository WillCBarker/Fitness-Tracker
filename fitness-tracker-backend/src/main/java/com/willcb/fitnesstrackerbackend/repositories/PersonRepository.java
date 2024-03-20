package com.willcb.fitnesstrackerbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willcb.fitnesstrackerbackend.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // put queries here?
    
}

