package com.nbu.gradebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbu.gradebook.model.Role;
import com.nbu.gradebook.model.School;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static java.util.Collections.emptySet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String username;

    @JsonProperty("authorities")
    private Set<Role> authorities;
    private School school;
}
