package com.nbu.gradebook.service;

import com.nbu.gradebook.dto.SignUpDTO;
import com.nbu.gradebook.dto.TeacherDTO;
import com.nbu.gradebook.dto.UserDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Teacher;
import com.nbu.gradebook.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    TeacherDTO create(Teacher teacher, String schoolName) throws DuplicateException, NotFoundException;

    TeacherDTO update(Teacher updatedTeacher, long id) throws NotFoundException;

    List<TeacherDTO> findAllUsers();

    TeacherDTO findById(long id) throws NotFoundException;

    TeacherDTO findByUsername(String username) throws NotFoundException, InvalidValuesException;

    void changeRoleById(long id, String authority);

    void delete(long id) throws NotFoundException;

    void addQualification(long id, String subjectName) throws NotFoundException;
}
