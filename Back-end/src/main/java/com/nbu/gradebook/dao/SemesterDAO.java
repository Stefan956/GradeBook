package com.nbu.gradebook.dao;

import com.nbu.gradebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterDAO extends JpaRepository<User, Long> {
}
