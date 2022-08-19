package com.nbu.gradebook.service;

import com.nbu.gradebook.dto.ClassDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Class;
import com.nbu.gradebook.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {
    ClassDTO create(Class schoolClass) throws DuplicateException;

    ClassDTO update(Class schoolClass) throws NotFoundException;

    List<ClassDTO> findAllClasses();

    ClassDTO findByID(long id) throws NotFoundException;

    void addStudent(Student student, Class school_class) throws DuplicateException;

    void delete(long id) throws NotFoundException;
}
