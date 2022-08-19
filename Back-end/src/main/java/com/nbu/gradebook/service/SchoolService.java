package com.nbu.gradebook.service;

import com.nbu.gradebook.dto.SchoolDTO;
import com.nbu.gradebook.dto.SignUpDTO;
import com.nbu.gradebook.dto.UserDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.School;
import com.nbu.gradebook.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolService {

    SchoolDTO create(School school) throws DuplicateException;

    SchoolDTO update(User user) throws NotFoundException;

    List<SchoolDTO> findAllSchools();

    SchoolDTO findById(long id) throws NotFoundException;

    School findByName(String name) throws NotFoundException;

    void delete(long id) throws NotFoundException;
}
