package com.nbu.gradebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbu.gradebook.model.*;
import com.nbu.gradebook.model.Class;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.*;

import static java.util.Collections.emptySet;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
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
}
