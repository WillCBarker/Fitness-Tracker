package com.willcb.fitnesstrackerbackend.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.willcb.fitnesstrackerbackend.entities.Person;
import com.willcb.fitnesstrackerbackend.services.PersonService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @BeforeAll
    void setUp() {
        Person person = new Person();
        person.setAge(30); // Set age
        person.setGender("Male"); // Set gender
        person.setHeight(72.0); // Set height
        person.setName("John"); // Set name
        person.setWeight(70.0); // Set weight
        
        personService.createPerson(person);
    }

    @Test
    @DisplayName("Test that all People in the Person table are returned if no exception occurs.")
    void getAllPersonsSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/persons")
                .contentType(MediaType.APPLICATION_JSON));
        System.out.println("\n\n\n\n\n**************************\n");
        System.out.println(resultActions);
        resultActions.andExpect(status().isOk());
    }
}
