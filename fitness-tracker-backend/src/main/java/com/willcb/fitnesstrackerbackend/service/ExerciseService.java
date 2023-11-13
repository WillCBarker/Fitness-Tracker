package com.willcb.fitnesstrackerbackend.service;

import com.willcb.fitnesstrackerbackend.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository ExerciseRepository;

}
