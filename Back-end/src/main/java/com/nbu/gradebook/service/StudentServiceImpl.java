package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.ClassDAO;
import com.nbu.gradebook.dao.ParentDAO;
import com.nbu.gradebook.dao.StudentDAO;
import com.nbu.gradebook.dto.DirectorDTO;
import com.nbu.gradebook.dto.StudentDTO;
import com.nbu.gradebook.dto.TeacherDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.*;
import com.nbu.gradebook.model.Class;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDAO studentRepo;

    @Autowired
    private ClassDAO classRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private ParentDAO parentRepo;

    @Autowired
    private ParentService parentService;

    @Override
    public StudentDTO create(Student student, String schoolName) throws DuplicateException, NotFoundException {
        if (studentRepo.existsByUsername((student.getUsername()))) {
            throw new DuplicateException("Username is already taken!");
        }

        if (student == null) {
            throw new NullPointerException("Object is null.");
        }

        if (student.getAuthorities().isEmpty()) {
            Role role = modelMapper.map(roleService.findByAuthority("ROLE_STUDENT"), Role.class);
            if (role == null) {
                throw new NotFoundException("Role is not found");
            }
            Set<Role> authorities = new HashSet<>();
            authorities.add(role);
            student.setAuthorities(authorities);
        }

        student.setSchool(modelMapper.map(schoolService.findByName(schoolName), School.class));
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        return modelMapper.map(studentRepo.save(student), StudentDTO.class);
    }

    @Override
    public StudentDTO update(Student updatedStudent, long id) throws NotFoundException {
        if (updatedStudent == null) {
            throw new NullPointerException("Student object is null!");
        }

        roleService.findByAuthority("ROLE_STUDENT");

        Optional<Student> student = studentRepo.findById(id);
        student.get().setUsername(updatedStudent.getUsername());
        student.get().setPassword(passwordEncoder.encode(updatedStudent.getPassword()));
        student.get().setAuthorities(updatedStudent.getAuthorities());
        student.get().setFirstName(updatedStudent.getFirstName());
        student.get().setLastName(updatedStudent.getLastName());
        if (updatedStudent.getParents() != null) student.get().setParents(updatedStudent.getParents());
        if (updatedStudent.getSchool() != null) student.get().setSchool(updatedStudent.getSchool());
        if (updatedStudent.getClassEntity() != null) student.get().setClassEntity(updatedStudent.getClassEntity());
        if (updatedStudent.getSemester() != null) student.get().setSemester(updatedStudent.getSemester());
        if (updatedStudent.getGrades() != null)  student.get().setGrades(updatedStudent.getGrades());
        if (updatedStudent.getAbsences() != null) student.get().setAbsences(updatedStudent.getAbsences());
        if (updatedStudent.getSubjects() != null)  student.get().setSubjects(updatedStudent.getSubjects());

        return modelMapper.map(studentRepo.findById(id).map(editedStudent -> studentRepo.save(student.get())).get(), StudentDTO.class);
    }

    @Override
    public List<StudentDTO> findAllUsers() {
        return studentRepo.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public StudentDTO findById(long id) throws NotFoundException {
        return modelMapper.map(studentRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_STUDENT_BY_ID_MESSAGE)), StudentDTO.class);
    }

    @Override
    public StudentDTO findByUsername(String username) throws NotFoundException, InvalidValuesException {
        return modelMapper.map(studentRepo.findByUsername(username).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_NAME_MESSAGE)), StudentDTO.class);
    }

    @Override
    public StudentDTO assignClass(String studentUsername, String className, String classYear) throws NotFoundException {
        Class classEntity = classRepo.findByNameAndYear(className, classYear).orElseThrow(() -> new NotFoundException("Class is not found!"));

        Student student = studentRepo.findByUsername(studentUsername).orElseThrow(() -> new NotFoundException("Student is not found!"));

        student.setClassEntity(classEntity);
        return modelMapper.map(studentRepo.findById(student.getId()).map(editedStudent -> studentRepo.save(student)).get(), StudentDTO.class);
    }

    @Override
    public Student addParent(Student student, String parentName) throws NotFoundException {
        Parent parent = parentRepo.findByUsername(parentName).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_PARENT_BY_ID_MESSAGE));
        if (student.getParents() == null) {
            Set<Parent> parents = new HashSet<>();
            parents.add(parent);
            student.setParents(parents);
        } else {
            if (!student.getParents().contains(parent))
            student.getParents().add(parent);
        }

        if (parent.getChildren() == null) {
            Set<Student> children = new HashSet<>();
            children.add(student);
            parent.setChildren(children);
        } else {
            if (!parent.getChildren().contains(student))
            parent.getChildren().add(student);
        }

        parentService.update(parent, parent.getId());

        return student;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        studentRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_STUDENT_BY_ID_MESSAGE));
        studentRepo.deleteById(id);
    }
}
