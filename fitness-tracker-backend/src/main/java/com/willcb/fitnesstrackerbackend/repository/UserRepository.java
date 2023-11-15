package com.willcb.fitnesstrackerbackend.repository;

import com.willcb.fitnesstrackerbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // put queries here?
}

