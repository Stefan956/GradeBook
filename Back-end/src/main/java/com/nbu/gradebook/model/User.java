package com.nbu.gradebook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

import static java.util.Collections.emptySet;
import static javax.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
public class User extends BaseUser {

    public User(String username, String password, String firstName, String lastName, Set<Role> authorities) {
        super(username, password, firstName, lastName, authorities);
    }

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities = emptySet();

    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }
}
