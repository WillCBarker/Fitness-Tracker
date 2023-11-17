package com.willcb.fitnesstrackerbackend.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.NoSuchElementException;

import com.willcb.fitnesstrackerbackend.entities.Person;
import com.willcb.fitnesstrackerbackend.entities.WorkoutPlan;
import com.willcb.fitnesstrackerbackend.services.PersonService;


@RestController
@RequestMapping("/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() {
        // need service method
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonByID(@PathVariable Long id) {

        try {
            Person person = personService.getPersonByID(id);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/workoutplan")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanByPersonID(@PathVariable Long id) {
        
        try {
            Person person = personService.getPersonByID(id);
            WorkoutPlan workoutPlan = person.getWorkoutPlan();
            return ResponseEntity.ok(workoutPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {

        try {
            Person createdPerson = personService.createPerson(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {

        try {
            Person person = personService.updatePerson(id, updatedPerson);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deletePerson(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
