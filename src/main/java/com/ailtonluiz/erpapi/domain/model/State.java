package com.ailtonluiz.erpapi.domain.model;

import com.ailtonluiz.erpapi.core.validation.Groups;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class State {

    @NotNull(groups = Groups.StateId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String shortName;


    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}