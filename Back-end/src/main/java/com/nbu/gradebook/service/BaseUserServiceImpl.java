package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.*;
import com.nbu.gradebook.model.BaseUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(noRollbackFor=Exception.class)
public class BaseUserServiceImpl implements BaseUserService {

    @Autowired
    private UserDAO userRepo;

    @Autowired
    private DirectorDAO directorRepo;

    @Autowired
    private StudentDAO studentRepo;

    @Autowired
    private TeacherDAO teacherRepo;

    @Autowired
    private ParentDAO parentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseUser findUserByUsername(String username) {
        BaseUser baseUser;
        try {
            baseUser = userRepo.findByUsername(username).get();
            return baseUser;
        } catch (Exception e) {
            System.out.println("Username doesn't exist in User entity.");
        }

        try {
            baseUser = directorRepo.findByUsername(username).get();
            return baseUser;
        } catch (Exception e) {
            System.out.println("Username doesn't exist in Director entity.");
        }

        try {
            baseUser = studentRepo.findByUsername(username).get();
            return baseUser;
        } catch (Exception e) {
            System.out.println("Username doesn't exist in Student entity.");
        }

        try {
            baseUser = teacherRepo.findByUsername(username).get();
            return baseUser;
        } catch (Exception e) {
            System.out.println("Username doesn't exist in Teacher entity.");
        }

        try {
            baseUser = parentRepo.findByUsername(username).get();
            return baseUser;
        } catch (Exception e) {
            System.out.println("Username doesn't exist in Parent entity.");
        }

        return null;
    }
}
