package com.nbu.gradebook.dao;

import com.nbu.gradebook.model.Class;
import com.nbu.gradebook.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassDAO extends JpaRepository<Class, Long> {
    Optional<Class> findByNameAndYear(String name, String year);
}
