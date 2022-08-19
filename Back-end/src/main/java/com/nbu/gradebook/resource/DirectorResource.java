package com.nbu.gradebook.resource;

import com.nbu.gradebook.service.ParentService;
import com.nbu.gradebook.service.StudentService;
import com.nbu.gradebook.service.SubjectService;
import com.nbu.gradebook.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

@RestController
@RequestMapping(API + DIRECTOR_PATH)
public class DirectorResource {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ParentService parentService;

    @GetMapping("/subjects")
    public ResponseEntity<?> getSubjects() {
        return ResponseEntity.ok(subjectService.getSubjects());
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getTeachers() {
        return ResponseEntity.ok(teacherService.findAllUsers());
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(studentService.findAllUsers());
    }

    @GetMapping("/parents")
    public ResponseEntity<?> getParents() {
        return ResponseEntity.ok(parentService.findAllUsers());
    }
}
