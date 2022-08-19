package com.nbu.gradebook.dao;

import com.nbu.gradebook.model.Parent;
import com.nbu.gradebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentDAO extends JpaRepository<Parent, Long> {
    Optional<Parent> findByUsername(String username);
    Boolean existsByUsername(String username);
}
