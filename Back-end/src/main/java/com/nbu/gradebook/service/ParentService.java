package com.nbu.gradebook.service;

import com.nbu.gradebook.dto.DirectorDTO;
import com.nbu.gradebook.dto.ParentDTO;
import com.nbu.gradebook.dto.StudentDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Parent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParentService {

    ParentDTO create(Parent parent) throws DuplicateException, NotFoundException;

    ParentDTO update(Parent updatedParent, long id) throws NotFoundException;

    List<ParentDTO> findAllUsers();

    List<StudentDTO> getParentChildren(String username) throws NotFoundException;

    ParentDTO findById(long id) throws NotFoundException;

    ParentDTO findByUsername(String username) throws NotFoundException, InvalidValuesException;

    void delete(long id) throws NotFoundException;
}
