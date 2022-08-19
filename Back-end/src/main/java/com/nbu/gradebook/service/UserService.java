package com.nbu.gradebook.service;

import com.nbu.gradebook.dto.SignUpDTO;
import com.nbu.gradebook.dto.UserDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    UserDTO register(SignUpDTO signUpDTO) throws DuplicateException, NotFoundException;

    UserDTO update(User user) throws NotFoundException;

    List<UserDTO> findAllUsers();

    UserDTO findById(long id) throws NotFoundException;

    UserDTO findByUsername(String username) throws NotFoundException, InvalidValuesException;

    void changeRoleById(long id, String authority);

    void delete(long id) throws NotFoundException;
}
