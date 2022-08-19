package com.nbu.gradebook.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfirmationCodeGenerator")
//    @TableGenerator(table = "SEQUENCES", name = "ConfirmationCodeGenerator")
    @Column(name = "id")
    private long id;
}
