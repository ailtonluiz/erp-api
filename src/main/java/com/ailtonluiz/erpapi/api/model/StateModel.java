package com.ailtonluiz.erpapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class StateModel {

    private Long id;
    private String name;
    private String shortName;
    private String status;

    private OffsetDateTime creationDate;

    private OffsetDateTime updateDate;
}
