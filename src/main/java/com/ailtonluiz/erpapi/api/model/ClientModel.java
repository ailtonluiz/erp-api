package com.ailtonluiz.erpapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientModel {

    private Long id;
    private String name;
    private String email;
    private String status;
}
