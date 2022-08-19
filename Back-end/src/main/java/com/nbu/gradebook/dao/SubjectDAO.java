package com.nbu.gradebook.dao;

import com.nbu.gradebook.model.Subject;
import com.nbu.gradebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectDAO extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
}