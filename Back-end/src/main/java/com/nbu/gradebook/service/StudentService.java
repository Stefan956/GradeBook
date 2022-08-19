package com.nbu.gradebook.service;

import com.nbu.gradebook.dto.DirectorDTO;
import com.nbu.gradebook.dto.StudentDTO;
import com.nbu.gradebook.dto.TeacherDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Class;
import com.nbu.gradebook.model.Director;
import com.nbu.gradebook.model.Student;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentDTO create(Student student, String schoolName) throws DuplicateException, NotFoundException;

    StudentDTO update(Student updatedStudent, long id) throws NotFoundException;

    List<StudentDTO> findAllUsers();

    StudentDTO findById(long id) throws NotFoundException;

    StudentDTO findByUsername(String username) throws NotFoundException, InvalidValuesException;

    StudentDTO assignClass(String studentUsername, String className, String classYear) throws NotFoundException;

    Student addParent(Student student, String parentName) throws NotFoundException;

    void delete(long id) throws NotFoundException;

}
