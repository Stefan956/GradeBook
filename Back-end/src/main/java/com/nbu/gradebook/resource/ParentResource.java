package com.nbu.gradebook.resource;


import com.nbu.gradebook.dto.StudentDTO;
import com.nbu.gradebook.model.Student;
import com.nbu.gradebook.service.ParentService;
import com.nbu.gradebook.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.util.List;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

@RestController
@RequestMapping(API + PARENT_PATH)
@PreAuthorize(IS_AUTHENTICATED)
public class ParentResource {

    @Autowired
    private ParentService parentService;

    @Autowired
    private StudentService studentService;

    //HTTP request for Student
    @PostMapping(STUDENT_PATH + "/create")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, @RequestParam(name = "schoolname") String schoolName, @RequestParam(name = "anotherparent") String anotherParent, Principal principal) {
        try {
//            student = studentService.addParent(student, principal.getName());
//            student = studentService.addParent(student, anotherParent);
            StudentDTO studentDTO = studentService.create(student, schoolName);
            student = studentService.addParent(student, principal.getName());
            student = studentService.addParent(student, anotherParent);
            studentDTO = studentService.update(student, studentDTO.getId());
            return ResponseEntity.ok(studentDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(STUDENT_PATH + "/update/{id}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody Student student, @PathVariable long id) {
        try {
            StudentDTO studentDTO = studentService.update(student, id);
            return ResponseEntity.ok(studentDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(STUDENT_PATH + "/assign-class")
    public ResponseEntity<?> assignNewClass(@RequestParam(name = "studentname") String studentName, @RequestParam(name = "classname") String className, @RequestParam(name = "classyear") String classYear ) {
        try {
            StudentDTO studentDTO = studentService.assignClass(studentName, className, classYear);
            return ResponseEntity.ok(studentDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(STUDENT_PATH + "/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable  long id) {
        try {
            studentService.findById(id);
            studentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/children")
    public ResponseEntity<?> getChildren(Principal principal) {
        try {
            List<StudentDTO> children = parentService.getParentChildren(principal.getName());
            return ResponseEntity.ok(children);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //End of HTTP requests for Student
}
