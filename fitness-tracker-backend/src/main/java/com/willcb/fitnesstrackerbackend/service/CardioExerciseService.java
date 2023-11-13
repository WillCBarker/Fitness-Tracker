package com.willcb.fitnesstrackerbackend.service;

import com.willcb.fitnesstrackerbackend.repository.CardioExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardioExerciseService {

    @Autowired
    private CardioExerciseRepository CardioExerciseRepository;

}