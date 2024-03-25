package com.willcb.fitnesstrackerbackend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import com.willcb.fitnesstrackerbackend.controllers.ExerciseController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExerciseController exerciseController;
    
    @Test
    void contextLoads() throws Exception {
        assertThat(exerciseController).isNotNull();
    }

    @Test
    public void getAllExercisesSuccess() throws Exception {
        this.mockMvc.perform(get("/exercises")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }
}
