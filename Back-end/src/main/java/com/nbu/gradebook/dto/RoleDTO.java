package com.nbu.gradebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    @NotNull
    private long id;

    @NotNull
    @JsonProperty("authority")
    private String authority;
}
