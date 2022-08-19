package com.nbu.gradebook.service;

import com.nbu.gradebook.dto.RoleDTO;
import com.nbu.gradebook.dto.UserDTO;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Role;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface RoleService {

    RoleDTO create(String authority) throws InvalidValuesException;

    RoleDTO update(long id, String authority) throws NotFoundException,InvalidValuesException;

    List<RoleDTO> findAllRoles();

    RoleDTO findById(long id) throws NotFoundException;

    RoleDTO findByAuthority(String authority) throws NotFoundException;

    void delete(long id) throws NotFoundException;
}

