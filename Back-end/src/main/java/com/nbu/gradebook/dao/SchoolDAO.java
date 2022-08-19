package com.nbu.gradebook.dao;

import com.nbu.gradebook.model.School;
import com.nbu.gradebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolDAO extends JpaRepository<School, Long> {
    Optional<School> findByName(String name);
    Boolean existsByName(String name);
}
