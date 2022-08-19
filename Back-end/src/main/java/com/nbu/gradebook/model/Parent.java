package com.nbu.gradebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;
import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parents")
public class Parent extends BaseUser {
    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "parents_roles", joinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities = emptySet();

    @JsonIgnore
    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "parents_children",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
//    @JoinTable(name = "parents_children", joinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "child_id", referencedColumnName = "id"))
    private Set<Student> children;

    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }
}
