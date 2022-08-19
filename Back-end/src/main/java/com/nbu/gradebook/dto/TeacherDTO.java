package com.nbu.gradebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbu.gradebook.model.*;
import com.nbu.gradebook.model.Class;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptySet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @JsonProperty("username")
    private String username;

    @JsonProperty("authorities")
    private Set<Role> authorities;
    private School school;
    private List<Class> classes;
    private Set<Subject> subjects;
}
