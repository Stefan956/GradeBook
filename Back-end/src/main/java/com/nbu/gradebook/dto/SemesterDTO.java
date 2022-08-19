package com.nbu.gradebook.dto;

import com.nbu.gradebook.model.Student;
import com.nbu.gradebook.model.Subject;
import com.nbu.gradebook.model.Subject2;
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
public class SemesterDTO {
    private short semesterNum;
    private List<Student> students;
    private Set<Subject> subjects;
}
