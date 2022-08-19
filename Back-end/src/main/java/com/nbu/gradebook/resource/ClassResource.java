package com.nbu.gradebook.resource;

import com.nbu.gradebook.dto.ClassDTO;
import com.nbu.gradebook.model.Class;
import com.nbu.gradebook.model.Student;
import com.nbu.gradebook.service.ClassService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

@RestController
@RequestMapping(API + CLASS_PATH)
@PreAuthorize(IS_AUTHENTICATED)
public class ClassResource {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClassService classService;

    //need to test this later on
    public ResponseEntity<?> addStudent(@Valid @RequestBody Class schoolClass, Student student) {
        try {
            classService.addStudent(student, schoolClass);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
