package com.nbu.gradebook.dto;

import com.nbu.gradebook.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
    private String name;
    private String year;
    private Teacher teacher;
    private Set<Student> students;
    private School school;
    private Set<Subject> subjects;
}
