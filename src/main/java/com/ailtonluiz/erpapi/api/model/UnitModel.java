package com.ailtonluiz.erpapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UnitModel {

    private Long id;
    private String description;
    private String shortDescription;
    private String status;

    private OffsetDateTime creationDate;

    private OffsetDateTime updateDate;
}
