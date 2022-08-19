package com.nbu.gradebook.resource;

import com.nbu.gradebook.dto.SchoolDTO;
import com.nbu.gradebook.model.School;
import com.nbu.gradebook.service.SchoolService;
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
@RequestMapping(API + SCHOOL_PATH)
@PreAuthorize(IS_AUTHENTICATED)
public class SchoolResource {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public ResponseEntity<?> createSchool(@Valid @RequestBody School school) {
        try {
            SchoolDTO schoolDTO = schoolService.create(school);
            return ResponseEntity.ok(schoolDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
