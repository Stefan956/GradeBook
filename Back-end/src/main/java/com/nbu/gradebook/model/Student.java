package com.nbu.gradebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

import static java.util.Collections.emptySet;
import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
@Table(name = "students")
public class Student extends BaseUser {
    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "students_roles", joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities = emptySet();

    @JsonIgnore
    @ManyToMany(mappedBy = "children", fetch = EAGER)
//    @ManyToMany
//    @JoinTable(
//            name = "child_parent",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<Parent> parents;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_school")
    private School school;

    @JsonIgnore
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "student_class")
    private Class classEntity;

    @JsonIgnore
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "student_semester")
    private Semester semester;

    @JsonIgnore
    @OneToMany(fetch = EAGER)
    private Set<Grades> grades;
//    private Map<Subject, List<Double>> grades;

    @JsonIgnore
    @OneToMany(fetch = EAGER)
    private Set<Absences> absences;
//    private Map<Subject, List<Date>> absences;

    @JsonIgnore
    @OneToMany(fetch = EAGER)
    private Set<Subject> subjects;

    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }
}
