package com.ailtonluiz.erpapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    private String phone;
    private String zipCode;
    private String number;
    private String complement;
    private String district;
    private CitySummaryModel city;
}
