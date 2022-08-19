package com.nbu.gradebook.service;

import com.nbu.gradebook.commons.utilities.Mapper;
import com.nbu.gradebook.dao.SchoolDAO;
import com.nbu.gradebook.dto.SchoolDTO;
import com.nbu.gradebook.dto.UserDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.School;
import com.nbu.gradebook.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDAO schoolRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SchoolDTO create(School school) throws DuplicateException {
        if (schoolRepo.existsByName(school.getName())) {
            throw new DuplicateException("Name is already taken!");
        }

        if (school == null) {
            throw new NullPointerException("Object is null.");
        }

        return modelMapper.map(schoolRepo.save(school), SchoolDTO.class);
    }

    @Override
    public SchoolDTO update(User user) throws NotFoundException {
        return null;
    }

    @Override
    public List<SchoolDTO> findAllSchools() {
        return null;
    }

    @Override
    public SchoolDTO findById(long id) throws NotFoundException {
        return modelMapper.map(schoolRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_SCHOOL_BY_ID_MESSAGE)), SchoolDTO.class);
    }

    @Override
    public School findByName(String schoolName) throws NotFoundException {
//        return modelMapper.map(schoolRepo.findByName(schoolName).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_SCHOOL_BY_NAME_MESSAGE)), SchoolDTO.class);
        return schoolRepo.findByName(schoolName).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_SCHOOL_BY_NAME_MESSAGE));
    }

    @Override
    public void delete(long id) throws NotFoundException {

    }
}
