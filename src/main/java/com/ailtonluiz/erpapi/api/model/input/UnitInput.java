package com.ailtonluiz.erpapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UnitInput {

    @NotBlank
    private String description;

    @NotBlank
    private String shortDescription;

    private String status;
}
