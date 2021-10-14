package com.ailtonluiz.erpapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionModel {

    private Long id;
    private String name;
    private String description;
    private String status;
}