package com.ailtonluiz.erpapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CityModel {

    private Long id;

    private String name;

    private String status;

    private OffsetDateTime creationDate;

    private OffsetDateTime updateDate;

    private StateSummaryModel state;

}
