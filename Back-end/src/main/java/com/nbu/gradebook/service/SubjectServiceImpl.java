package com.nbu.gradebook.service;

import com.nbu.gradebook.dao.SubjectDAO;
import com.nbu.gradebook.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDAO subjectRepo;

    @Override
    public Set<Subject> getSubjects() {
        return subjectRepo.findAll().stream().collect(Collectors.toSet());
    }
}
