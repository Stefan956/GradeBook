package com.nbu.gradebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;
import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher extends BaseUser {

    public Teacher(String username, String password, String firstName, String lastName, Set<Role> authorities) {
        super(username, password, firstName, lastName, authorities);
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "users_school")
    private School school;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = EAGER)
    private Set<Class> classes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = EAGER)
    private Set<Subject> subjects;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "teachers_roles", joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities = emptySet();

    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }
}
