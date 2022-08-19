package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.ClassDAO;
import com.nbu.gradebook.dto.ClassDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Class;
import com.nbu.gradebook.model.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.nbu.gradebook.commons.paths.UserConstants.UNABLE_TO_FIND_CLASS_BY_ID_MESSAGE;

public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassDAO classRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClassDTO create(Class schoolClass) throws DuplicateException {
        if (classRepo.existsById(schoolClass.getId())) {
            throw new DuplicateException("Class already exists!");
        }

        if (schoolClass == null) {
            throw new NullPointerException("Object is null!");
        }

        return modelMapper.map(classRepo.save(schoolClass), ClassDTO.class);
    }

    @Override
    public ClassDTO update(Class schoolClass) throws NotFoundException {
        return null;
    }

    @Override
    public List<ClassDTO> findAllClasses() { return null; }

    @Override
    public ClassDTO findByID(long id) throws NotFoundException {
        return modelMapper.map(classRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_CLASS_BY_ID_MESSAGE)), ClassDTO.class);
    }

    @Override
    public void addStudent(Student student, Class schoolClass) throws DuplicateException{
        if (schoolClass.getStudents().contains(student)) {
            throw new DuplicateException("Student is already in the class!");
        }

        if (schoolClass == null) {
            throw new NullPointerException("Class is null!");
        }

        if (student == null) {
            throw new NullPointerException("Student is null!");
        }

        //not sure if we have to add model mapper here
        schoolClass.getStudents().add(student);
    }

    @Override
    public void delete(long id) throws NotFoundException {

    }
}
