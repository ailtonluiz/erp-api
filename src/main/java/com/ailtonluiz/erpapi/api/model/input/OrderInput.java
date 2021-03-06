package com.ailtonluiz.erpapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class OrderInput {


    @Valid
    @NotNull
    private PaymentMethodIdInput paymentMethod;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemOrderInput> items;
}
