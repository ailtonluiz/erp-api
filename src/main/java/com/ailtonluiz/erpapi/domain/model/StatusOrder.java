package com.ailtonluiz.erpapi.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusOrder {CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELED("Canceled", CREATED);


    private String description;
    private List<StatusOrder> previousStatus;

    StatusOrder(String description, StatusOrder... previousStatus) {
        this.description = description;
        this.previousStatus = Arrays.asList(previousStatus);
    }


    public String getDescription() {
        return this.description;
    }


    public boolean cannotChangeTo(StatusOrder newStatus) {
        return !newStatus.previousStatus.contains(this);
    }
}


