package com.nbu.gradebook.dao;

import com.nbu.gradebook.model.Director;
import com.nbu.gradebook.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorDAO extends JpaRepository<Director, Long> {
    Optional<Director> findByUsername(String username);
    Boolean existsByUsername(String username);
}
