package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.DirectorDAO;
import com.nbu.gradebook.dto.DirectorDTO;
import com.nbu.gradebook.dto.TeacherDTO;
import com.nbu.gradebook.exception.DuplicateException;
import com.nbu.gradebook.exception.InvalidValuesException;
import com.nbu.gradebook.exception.NotFoundException;
import com.nbu.gradebook.model.Director;
import com.nbu.gradebook.model.Role;
import com.nbu.gradebook.model.School;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorDAO directorRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SchoolService schoolService;

    @Override
    public DirectorDTO create(Director director, String schoolName) throws DuplicateException, NotFoundException {
        if (directorRepo.existsByUsername((director.getUsername()))) {
            throw new DuplicateException("Username is already taken!");
        }

        if (director == null) {
            throw new NullPointerException("Object is null.");
        }

        if (director.getAuthorities().isEmpty()) {
            Role role = modelMapper.map(roleService.findByAuthority("ROLE_DIRECTOR"), Role.class);
            if (role == null) {
                throw new NotFoundException("Role is not found");
            }
            Set<Role> authorities = new HashSet<>();
            authorities.add(role);
            director.setAuthorities(authorities);
        }

        director.setSchool(schoolService.findByName(schoolName));

        return modelMapper.map(directorRepo.save(new Director(director.getUsername(), passwordEncoder.encode(director.getPassword()), director.getFirstName(), director.getLastName(), director.getAuthorities())), DirectorDTO.class);
    }

    @Override
    public DirectorDTO update(Director updatedDirector, long id) throws NotFoundException {
        if (updatedDirector == null) {
            throw new NullPointerException("Director object is null!");
        }

        roleService.findByAuthority("ROLE_DIRECTOR");

        Optional<Director> director = directorRepo.findById(id);
        director.get().setUsername(updatedDirector.getUsername());
        director.get().setPassword(passwordEncoder.encode(updatedDirector.getPassword()));
        director.get().setAuthorities(updatedDirector.getAuthorities());
        director.get().setFirstName(updatedDirector.getFirstName());
        director.get().setLastName(updatedDirector.getLastName());
        return modelMapper.map(directorRepo.findById(id).map(editedDirector -> directorRepo.save(director.get())).orElseThrow(), DirectorDTO.class);
    }

    @Override
    public DirectorDTO findById(long id) throws NotFoundException {
        return modelMapper.map(directorRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_DIRECTOR_BY_ID_MESSAGE)), DirectorDTO.class);
    }

    @Override
    public DirectorDTO findByUsername(String username) throws NotFoundException, InvalidValuesException {
        return modelMapper.map(directorRepo.findByUsername(username).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_USER_BY_NAME_MESSAGE)), DirectorDTO.class);

    }

    @Override
    public void delete(long id) throws NotFoundException {
        directorRepo.findById(id).orElseThrow(() -> new NotFoundException(UNABLE_TO_FIND_DIRECTOR_BY_ID_MESSAGE));
        directorRepo.deleteById(id);
    }
}
