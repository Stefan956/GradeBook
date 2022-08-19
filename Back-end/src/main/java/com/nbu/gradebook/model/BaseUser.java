package com.nbu.gradebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

import static java.util.Collections.emptySet;
import static javax.persistence.FetchType.EAGER;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Data
public class BaseUser extends BaseEntity implements UserDetails  {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

//    @ManyToMany(fetch = EAGER)
//    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> authorities = emptySet();

    @Column
    private String firstName;

    @Column
    private String lastName;

    public BaseUser(String username, String password, String firstName, String lastName, Set<Role> authorities) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

//    public BaseUser(String username, String password, String firstName, String lastName, Set<Role> authorities) {
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.authorities = authorities;
//    }

    public Set<Role> getAuthorities() {
        return null;
    }


    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}
