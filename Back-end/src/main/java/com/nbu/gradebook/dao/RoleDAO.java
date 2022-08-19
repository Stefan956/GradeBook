package com.nbu.gradebook.dao;

import com.nbu.gradebook.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
//    @Query("SELECT u FROM Role u WHERE u.authority = ?1")
//@Query(
//        value = "SELECT * FROM roles u WHERE u.role = ?",
//        nativeQuery = true)
Optional<Role> findByAuthority(String authority);
}