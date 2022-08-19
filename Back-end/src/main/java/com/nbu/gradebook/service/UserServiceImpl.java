package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.RoleDAO;
import com.nbu.gradebook.dao.UserDAO;
import com.nbu.gradebook.dto.RoleDTO;
import com.nbu.gradebook.dto.SignUpDTO;
import com.nbu.gradebook.dto.UserDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Role;
import com.nbu.gradebook.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDTO register(SignUpDTO signUpDTO) throws DuplicateException, NotFoundException {

        if (userRepo.existsByUsername(signUpDTO.getUsername())) {
            throw new DuplicateException("Username is already taken!");
        }

        if (signUpDTO == null) {
            throw new NullPointerException("Object is null.");
        }

        Role role = (userRepo.findAll().isEmpty()) ? modelMapper.map(roleService.findByAuthority("ROLE_ADMIN"), Role.class) : modelMapper.map(roleService.findByAuthority("ROLE_USER"), Role.class);

        if (role == null) {
            throw new NotFoundException("Role is not found");
        }

        User user = new User(signUpDTO.getUsername(), passwordEncoder.encode(signUpDTO.getPassword()), signUpDTO.getFirstName(), signUpDTO.getLastName(), Collections.singleton(role));

        return modelMapper.map(userRepo.save(user), UserDTO.class);
    }

    @Override
    public UserDTO update(User user) throws NotFoundException {
        if (user == null) {
            throw new NullPointerException("Object is null.");
        }

        for (Role role : user.getAuthorities()) {
            roleService.findByAuthority(role.getAuthority());
        }

        return modelMapper.map(userRepo.saveAndFlush(user), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public UserDTO findById(long id) throws NotFoundException {
        return modelMapper.map(userRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_ID_MESSAGE)), UserDTO.class);
    }

    @Override
    public UserDTO findByUsername(String username) throws NotFoundException {
        return modelMapper.map(userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_NAME_MESSAGE)), UserDTO.class);
    }

    @Override
    public void changeRoleById(long id, String authority) {

    }

    @Override
    public void delete(long id) throws NotFoundException {
        userRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_ID_MESSAGE));
        userRepo.deleteById(id);
    }
}
