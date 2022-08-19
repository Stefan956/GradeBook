package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.RoleDAO;
import com.nbu.gradebook.dto.RoleDTO;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nbu.gradebook.commons.paths.UserConstants.UNABLE_TO_FIND_ROLE_BY_AUTHORITY_MESSAGE;
import static com.nbu.gradebook.commons.paths.UserConstants.UNABLE_TO_FIND_ROLE_BY_ID_MESSAGE;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDTO create(String authority) throws InvalidValuesException {
        if (authority.isEmpty() || authority == null) throw new InvalidValuesException("The provided authority is not valid.");
        Role role = new Role();
        role.setAuthority(authority);
        return modelMapper.map(roleRepo.saveAndFlush(role), RoleDTO.class);
    }

    @Override
    public RoleDTO update(long id, String authority) throws NotFoundException,InvalidValuesException {
        Role role = roleRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_ROLE_BY_ID_MESSAGE));
        if (authority.isEmpty() || authority == null) throw new InvalidValuesException("The provided authority is not valid.");
        role.setAuthority(authority);
        return modelMapper.map(roleRepo.saveAndFlush(role), RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findAllRoles() {
        return roleRepo.findAll()
                .stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public RoleDTO findById(long id) throws NotFoundException {
        return modelMapper.map(roleRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_ROLE_BY_ID_MESSAGE)), RoleDTO.class);
    }

    @Override
    public RoleDTO findByAuthority(String authority) throws NotFoundException {

        return modelMapper.map(roleRepo.findByAuthority(authority).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_ROLE_BY_AUTHORITY_MESSAGE)), RoleDTO.class);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        roleRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_ROLE_BY_ID_MESSAGE));
        roleRepo.deleteById(id);
    }
}
