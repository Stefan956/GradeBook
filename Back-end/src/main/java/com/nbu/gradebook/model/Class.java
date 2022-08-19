package com.nbu.gradebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "classes")
public class Class extends BaseEntity {
    private String name;

    private String year;

    @JsonIgnore
    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "class_teacher")
    private Teacher teacher;

    @JsonIgnore
    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, fetch = EAGER)
    private Set<Student> students;

    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = com.nbu.gradebook.model.School.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private School school;

    @JsonIgnore
    @OneToMany
    private Set<Subject> subjects;

}
