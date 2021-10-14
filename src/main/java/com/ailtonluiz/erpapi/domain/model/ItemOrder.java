package com.ailtonluiz.erpapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemOrder {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private BigDecimal priceUnity;
    private BigDecimal priceTotal;
    private Integer quantity;
    private String note;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public void calculatePriceTotal() {
        BigDecimal priceUnity = this.getPriceUnity();
        Integer quantity = this.getQuantity();

        if (priceUnity == null) {
            priceUnity = BigDecimal.ZERO;
        }
        if (quantity == null) {
            quantity = 0;
        }
        this.setPriceTotal(priceUnity.multiply(new BigDecimal(quantity)));

    }

}
