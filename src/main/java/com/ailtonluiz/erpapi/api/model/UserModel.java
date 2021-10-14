package com.ailtonluiz.erpapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;

}
