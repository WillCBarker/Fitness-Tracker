package com.willcb.fitnesstrackerbackend.service;

import com.willcb.fitnesstrackerbackend.repository.UserRepository;
import com.willcb.fitnesstrackerbackend.model.User;
import com.willcb.fitnesstrackerbackend.model.WorkoutPlan;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

     private void validateUser(User user) {
        // Validate user input
        if (user == null || user.getName() == null || user.getAge() <= 0 || user.getHeight() <= 0 || user.getWeight() <= 0 || user.getGender() == null) {
            throw new IllegalArgumentException("User details are incomplete");
        }
    }

    public User getUserByID(Long userID) {
        return userRepository.findById(userID).orElse(null);
    }

    public WorkoutPlan getWorkoutPlanByUserID(Long userID){
        User user = userRepository.findById(userID).orElseThrow(() -> new NoSuchElementException("User not found"));
        return user.getWorkoutPlan();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        validateUser(user);

        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        validateUser(updatedUser);
        User existingUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return userRepository.save(existingUser);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}