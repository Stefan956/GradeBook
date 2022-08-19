package com.nbu.gradebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static java.util.Collections.emptySet;
import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@Table(name = "directors")
public class Director extends BaseUser {

    public Director(String username, String password, String firstName, String lastName, Set<Role> authorities) {
        super(username, password, firstName, lastName, authorities);
    }

    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = com.nbu.gradebook.model.School.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_school")
    private School school;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "directors_roles", joinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities = emptySet();
}
