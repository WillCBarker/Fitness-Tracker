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

    /**
     *  Retrieves all people from database.
     * 
     *  @return list of person objects
     */
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    /**
     * Retrieves specific person by id.
     * 
     * @param id The ID of the person to be updated.
     * @return ResponseEntity<Person> on success,
     *         a bad request response if the provided data is invalid, 
     *         or a not found response if the person with the specified ID does not exist.
     */
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

    /**
     * Creates a new person.
     * 
     * @param person - The person object to be created.
     * @return ResponseEntity<Person> on success
     *         or a bad request response if the provided data is invalid.
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {

        try {
            Person createdPerson = personService.createPerson(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Updates an existing person with the specified ID.
     * 
     * @param id The ID of the person to be updated.
     * @param updatedPerson The updated information of the person.
     * @return ResponseEntity<Person> on success,
     *         a bad request response if the provided data is invalid, 
     *         or a not found response if the person with the specified ID does not exist.
     */
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

    /**
     * Deletes an existing person with the specified ID.
     * 
     * @param id The ID of the person to be deleted.
     * @return ResponseEntity<Void> on success,
     *         or a not found response if the person with the specified ID does not exist.
     */
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
