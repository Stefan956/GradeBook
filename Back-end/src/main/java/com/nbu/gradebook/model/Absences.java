package com.nbu.gradebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "absences")
public class Absences extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    private Student student;

    @JsonIgnore
    @ElementCollection
    private List<Date> absences;
}
