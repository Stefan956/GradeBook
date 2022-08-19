package com.nbu.gradebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbu.gradebook.model.*;
import com.nbu.gradebook.model.Class;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.*;

import static java.util.Collections.emptySet;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @JsonProperty("username")
    private String username;

    @JsonProperty("authorities")
    private Set<Role> authorities;
    private Set<Parent> parents;
    private School school;
    private Class classEntity;
    private Semester semester;
    private Set<Grades> grades;
    private Set<Absences> absences;
    private Set<Subject> subjects;
}
