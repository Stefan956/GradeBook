package com.nbu.gradebook.dto;

import com.nbu.gradebook.model.Class;
import com.nbu.gradebook.model.Director;
import com.nbu.gradebook.model.Student;
import com.nbu.gradebook.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {
    private long id;
    private String name;
    private String address;
    private Director director;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Class> classes;
}
