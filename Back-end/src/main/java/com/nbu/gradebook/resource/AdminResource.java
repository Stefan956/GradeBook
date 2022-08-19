package com.nbu.gradebook.resource;

import com.nbu.gradebook.dao.StudentDAO;
import com.nbu.gradebook.dto.DirectorDTO;
import com.nbu.gradebook.dto.ParentDTO;
import com.nbu.gradebook.dto.StudentDTO;
import com.nbu.gradebook.dto.TeacherDTO;
import com.nbu.gradebook.model.*;
import com.nbu.gradebook.model.Class;
import com.nbu.gradebook.service.DirectorService;
import com.nbu.gradebook.service.ParentService;
import com.nbu.gradebook.service.StudentService;
import com.nbu.gradebook.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.nbu.gradebook.commons.paths.UserConstants.*;

@RestController
@RequestMapping(API + ADMIN_PATH)
//@PreAuthorize(IS_AUTHENTICATED + ",hasRole('ROLE_ADMIN')")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminResource {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ParentService parentService;

    //HTTP Requests for Teacher entity + adding subjects that teacher can teach

    @GetMapping(TEACHER_PATH + "/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable long id) {
        try {
            TeacherDTO teacherDTO = teacherService.findById(id);
            return ResponseEntity.ok(teacherDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(TEACHER_PATH + "/create")
    public ResponseEntity<?> createTeacher(@Valid @RequestBody Teacher teacher, @RequestParam(name = "schoolname") String schoolName) {
        try {
            TeacherDTO teacherDTO = teacherService.create(teacher, schoolName);
            return ResponseEntity.ok(teacherDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(TEACHER_PATH + "/update" + "/{id}")
    public ResponseEntity<?> updateTeacher(@Valid @RequestBody Teacher teacher, @PathVariable long id) {
        try {
            TeacherDTO teacherDTO = teacherService.update(teacher, id);
            return ResponseEntity.ok(teacherDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(TEACHER_PATH + "/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable long id) {
        try {
            teacherService.findById(id);
            teacherService.delete(id);
            return ResponseEntity.ok("Object deleted successfully.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(TEACHER_PATH + "/{id}/add-subject")
    public ResponseEntity<?> addSubject(@PathVariable long id, @RequestParam(name = "subjectName") String subjectName) {
        try {
            teacherService.addQualification(id, subjectName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //End of HTTP requests for Teacher

    //HTTP requests for Director

    @GetMapping(DIRECTOR_PATH + "/{id}")
    public ResponseEntity<?> getDirector(@PathVariable long id) {
        try {
            DirectorDTO directorDTO = directorService.findById(id);
            return ResponseEntity.ok(directorDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(DIRECTOR_PATH + "/create")
    public ResponseEntity<?> createDirector(@Valid @RequestBody Director director, @RequestParam(name = "schoolname") String schoolName) {
        try {
            DirectorDTO directorDTO = directorService.create(director, schoolName);
            return ResponseEntity.ok(directorDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(DIRECTOR_PATH + "/update/{id}")
    public ResponseEntity<?> updateDirector(@Valid @RequestBody Director director, @PathVariable long id) {
        try {
            DirectorDTO directorDTO = directorService.update(director, id);
            return ResponseEntity.ok(directorDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(DIRECTOR_PATH + "/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable long id) {
        try {
            directorService.findById(id);
            directorService.delete(id);
            return ResponseEntity.ok("Object deleted successfully.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //End of HTTP request for Student

    //HTTP request for Student

    @GetMapping(STUDENT_PATH + "/{id}")
    public ResponseEntity<?> getStudent(@PathVariable long id) {
        try {
            StudentDTO studentDTO = studentService.findById(id);
            return ResponseEntity.ok(studentDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(STUDENT_PATH + "/create")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, @RequestParam(name = "schoolname") String schoolName) {
        try {
            StudentDTO studentDTO = studentService.create(student, schoolName);
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
    //End of HTTP requests for Student

    //HTTP Requests for Parent entity

    @GetMapping(PARENT_PATH + "/{id}")
    public ResponseEntity<?> getParent(@PathVariable long id) {
        try {
            ParentDTO parentDTO = parentService.findById(id);
            return ResponseEntity.ok(parentDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(PARENT_PATH + "/create")
    public ResponseEntity<?> createParent(@Valid @RequestBody Parent parent) {
        try {
            ParentDTO parentDTO = parentService.create(parent);
            return ResponseEntity.ok(parentDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(PARENT_PATH + "/update/{id}")
    public ResponseEntity<?> updateParent(@Valid @RequestBody Parent parent, @PathVariable long id) {
        try {
            ParentDTO parentDTO = parentService.update(parent, id);
            return ResponseEntity.ok(parentDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(PARENT_PATH + "/{id}")
    public ResponseEntity<?> deleteParent(@PathVariable  long id) {
        try {
            parentService.findById(id);
            parentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //End of HTTP requests for Parent
}
