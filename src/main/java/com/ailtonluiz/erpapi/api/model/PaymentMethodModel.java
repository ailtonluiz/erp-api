package com.ailtonluiz.erpapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodModel {

    private Long id;
    private String description;
    private String status;
}