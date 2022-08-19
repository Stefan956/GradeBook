package com.nbu.gradebook.model;

public class UserClassBefore {
}







//package com.nbu.gradebook.model;
//
//        import lombok.*;
//        import org.springframework.security.core.userdetails.UserDetails;
//
//        import javax.persistence.*;
//        import java.util.Set;
//
//        import static java.util.Collections.emptySet;
//        import static javax.persistence.FetchType.EAGER;
//
//@Entity
//@NoArgsConstructor
//@Data
//@Table(name = "users")
//public class User extends BaseEntity implements UserDetails {
//
//    @Column(name = "username", unique = true, nullable = false)
//    private String username;
//
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @ManyToMany(targetEntity = Role.class, fetch = EAGER)
//    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> authorities = emptySet();
//
//    @Column(nullable = false)
//    private String firstName;
//
//    @Column(nullable = false)
//    private String lastName;
//
//    public User(String username, String password, String firstName, String lastName, Set<Role> authorities) {
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.authorities = authorities;
//    }
//
//    public Set<Role> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    @Transient
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isEnabled() {
//        return true;
//    }
//
//}

