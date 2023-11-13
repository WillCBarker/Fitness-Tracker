package com.willcb.fitnesstrackerbackend.service;

import com.willcb.fitnesstrackerbackend.repository.ResistanceExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResistanceExerciseService {

    @Autowired
    private ResistanceExerciseRepository ResistanceExerciseRepository;

}
