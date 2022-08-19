package com.nbu.gradebook.model;

import static javax.persistence.FetchType.EAGER;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "semesters")
public class Semester extends BaseEntity {
    @Column(unique = true)
    private short semesterNum;

    @JsonIgnore
    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, fetch = EAGER)
    private List<Student> students;

    @JsonIgnore
    @OneToMany
    private List<Subject> subjects;
}
