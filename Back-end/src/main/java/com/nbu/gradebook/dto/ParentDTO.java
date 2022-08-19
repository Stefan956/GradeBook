package com.nbu.gradebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbu.gradebook.model.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Set;

import static java.util.Collections.emptySet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParentDTO {
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

    private Set<StudentDTO> children;
}
