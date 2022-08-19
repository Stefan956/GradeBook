package com.nbu.gradebook.service;

import com.nbu.gradebook.dto.DirectorDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Director;
import org.springframework.stereotype.Service;

@Service
public interface DirectorService {

    DirectorDTO create(Director director, String schoolName) throws DuplicateException, NotFoundException;

    DirectorDTO update(Director updatedDirector, long id) throws NotFoundException;

    DirectorDTO findById(long id) throws NotFoundException;

    DirectorDTO findByUsername(String username) throws NotFoundException, InvalidValuesException;

    void delete(long id) throws NotFoundException;
}
