package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.ParentDAO;
import com.nbu.gradebook.dto.ParentDTO;
import com.nbu.gradebook.dto.StudentDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Parent;
import com.nbu.gradebook.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

@Service
@Transactional
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentDAO parentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    @Override
    public ParentDTO create(Parent parent) throws DuplicateException, NotFoundException {
        if (parentRepo.existsByUsername(parent.getUsername())) {
            throw new DuplicateException("Username is already taken!");
        }

        if (parent == null) {
            throw new NullPointerException("Object is null.");
        }

        if (parent.getAuthorities().isEmpty()) {
            Role role = modelMapper.map(roleService.findByAuthority("ROLE_PARENT"), Role.class);
            if (role == null) {
                throw new NotFoundException("Role is not found");
            }
            Set<Role> authorities = new HashSet<>();
            authorities.add(role);
            parent.setAuthorities(authorities);
        }

        parent.setPassword(passwordEncoder.encode(parent.getPassword()));

        return modelMapper.map(parentRepo.save(parent), ParentDTO.class);
    }

    @Override
    public ParentDTO update(Parent updatedParent, long id) throws NotFoundException {
        if (updatedParent == null) {
            throw new NullPointerException("Object is null.");
        }

        roleService.findByAuthority("ROLE_PARENT");

        Parent parent = parentRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_PARENT_BY_ID_MESSAGE));
        parent.setUsername(updatedParent.getUsername());
        parent.setPassword(updatedParent.getPassword());
        parent.setAuthorities(updatedParent.getAuthorities());
        parent.setFirstName(updatedParent.getFirstName());
        parent.setLastName(updatedParent.getLastName());
        if (updatedParent.getChildren() != null) {
            parent.setChildren(updatedParent.getChildren());
        }

        return modelMapper.map(parentRepo.findById(id).map(editedParent -> parentRepo.save(parent)).get(), ParentDTO.class);
    }

    @Override
    public List<ParentDTO> findAllUsers() {
        return parentRepo.findAll()
                .stream()
                .map(parent -> modelMapper.map(parent, ParentDTO.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<StudentDTO> getParentChildren(String username) throws NotFoundException {
        Parent parent = parentRepo.findByUsername(username).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_PARENT_BY_ID_MESSAGE));
        return parent.getChildren()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ParentDTO findById(long id) throws NotFoundException {
        return modelMapper.map(parentRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_PARENT_BY_ID_MESSAGE)), ParentDTO.class);
    }

    @Override
    public ParentDTO findByUsername(String username) throws NotFoundException, InvalidValuesException {
        return modelMapper.map(parentRepo.findByUsername(username).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_PARENT_BY_NAME_MESSAGE)), ParentDTO.class);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        parentRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_PARENT_BY_ID_MESSAGE));
        parentRepo.deleteById(id);
    }
}
