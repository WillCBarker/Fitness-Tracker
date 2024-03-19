package com.willcb.fitnesstrackerbackend.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.NoSuchElementException;

import com.willcb.fitnesstrackerbackend.entities.Person;
import com.willcb.fitnesstrackerbackend.services.PersonService;


@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        // need service method
        return this.personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonByID(@PathVariable Long id) {

        try {
            Person person = this.personService.getPersonByID(id);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {

        try {
            Person createdPerson = this.personService.createPerson(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {

        try {
            Person person = this.personService.updatePerson(id, updatedPerson);
            return ResponseEntity.ok(person);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = this.personService.deletePerson(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
