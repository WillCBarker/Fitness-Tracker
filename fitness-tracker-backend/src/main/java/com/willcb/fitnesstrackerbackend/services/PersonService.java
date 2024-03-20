package com.willcb.fitnesstrackerbackend.services;

import com.willcb.fitnesstrackerbackend.entities.Person;
import com.willcb.fitnesstrackerbackend.entities.WorkoutPlan;
import com.willcb.fitnesstrackerbackend.repositories.PersonRepository;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {
    
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private void validatePerson(Person person) {
        // Validate person input
        if (person == null || person.getName() == null || person.getAge() <= 0 || person.getHeight() <= 0 || person.getWeight() <= 0 || person.getGender() == null) {
            throw new IllegalArgumentException("Person details are incomplete");
        }
    }

    public Person getPersonByID(int personID) {
        return personRepository.findById(personID)
                .orElseThrow(() -> new NoSuchElementException("Person with ID " + personID + " not found"));
    }

    public WorkoutPlan getWorkoutPlanByPersonID(int personID){
        Person person = personRepository.findById(personID).orElseThrow(() -> new NoSuchElementException("Person not found"));
        return person.getWorkoutPlan();
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person createPerson(Person person) {
        validatePerson(person);

        return personRepository.save(person);
    }

    public Person updatePerson(int id, Person updatedPerson) {
        validatePerson(updatedPerson);
        Person existingPerson = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Person not found"));
        return personRepository.save(existingPerson);
    }

    public boolean deletePerson(int id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
