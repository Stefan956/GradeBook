package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.SubjectDAO;
import com.nbu.gradebook.dao.TeacherDAO;
import com.nbu.gradebook.dao.UserDAO;
import com.nbu.gradebook.dto.DirectorDTO;
import com.nbu.gradebook.dto.SchoolDTO;
import com.nbu.gradebook.dto.TeacherDTO;
import com.nbu.gradebook.dto.UserDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Role;
import com.nbu.gradebook.model.School;
import com.nbu.gradebook.model.Subject;
import com.nbu.gradebook.model.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nbu.gradebook.commons.paths.UserConstants.UNABLE_TO_FIND_USER_BY_ID_MESSAGE;
import static com.nbu.gradebook.commons.paths.UserConstants.UNABLE_TO_FIND_USER_BY_NAME_MESSAGE;

public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private SubjectDAO subjectRepo;

    @Override
    public TeacherDTO create(Teacher teacher, String schoolName) throws DuplicateException, NotFoundException {
        if (teacherRepo.existsByUsername((teacher.getUsername()))) {
            throw new DuplicateException("Username is already taken!");
        }

        if (teacher == null) {
            throw new NullPointerException("Object is null.");
        }

        if (teacher.getAuthorities().isEmpty()) {
            Role role = modelMapper.map(roleService.findByAuthority("ROLE_TEACHER"), Role.class);
            if (role == null) {
                throw new NotFoundException("Role is not found");
            }
            Set<Role> authorities = new HashSet<>();
            authorities.add(role);
            teacher.setAuthorities(authorities);
        }

        teacher.setSchool(modelMapper.map(schoolService.findByName(schoolName), School.class));

        return modelMapper.map(teacherRepo.save(teacher), TeacherDTO.class);
    }

    @Override
    public TeacherDTO update(Teacher updatedTeacher, long id) throws NotFoundException {
        if (updatedTeacher == null) {
            throw new NullPointerException("Object is null.");
        }

        roleService.findByAuthority("ROLE_TEACHER");

        Optional<Teacher> teacher = teacherRepo.findById(id);
        teacher.get().setUsername(updatedTeacher.getUsername());
        teacher.get().setPassword(updatedTeacher.getPassword());
        teacher.get().setAuthorities(updatedTeacher.getAuthorities());
        teacher.get().setFirstName(updatedTeacher.getFirstName());
        teacher.get().setLastName(updatedTeacher.getLastName());
        if (updatedTeacher.getSubjects() != null) {
            teacher.get().setSubjects(updatedTeacher.getSubjects());
        }

        return modelMapper.map(teacherRepo.findById(id).map(editedTeacher -> teacherRepo.save(teacher.get())).get(), TeacherDTO.class);
    }

    @Override
    public List<TeacherDTO> findAllUsers() {
        return teacherRepo.findAll()
                .stream()
                .map(teacher -> modelMapper.map(teacher, TeacherDTO.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TeacherDTO findById(long id) throws NotFoundException {
        return modelMapper.map(teacherRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_ID_MESSAGE)), TeacherDTO.class);
    }

    @Override
    public TeacherDTO findByUsername(String username) throws NotFoundException, InvalidValuesException {
        return modelMapper.map(teacherRepo.findByUsername(username).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_NAME_MESSAGE)), TeacherDTO.class);

    }

    @Override
    public void changeRoleById(long id, String authority) {

    }

    @Override
    public void delete(long id) throws NotFoundException {
        teacherRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_ID_MESSAGE));
        teacherRepo.deleteById(id);
    }

    @Override
    public void addQualification(long id, String subjectName) throws NotFoundException {
        Teacher teacher = teacherRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_ID_MESSAGE));
        teacher.getSubjects().add(subjectRepo.findByName(subjectName).orElseThrow(() -> new NotFoundException("Subject is not found.")));
        update(teacher, id);
    }
}
