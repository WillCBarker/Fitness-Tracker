package com.willcb.fitnesstrackerbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willcb.fitnesstrackerbackend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // put queries here?
}

