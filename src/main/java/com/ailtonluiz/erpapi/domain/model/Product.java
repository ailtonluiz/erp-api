package com.ailtonluiz.erpapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    private Integer stock;

    private BigDecimal salePrice;

    private BigDecimal purchasePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Unit unit;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ProductsGroup productsGroup;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ProductsSubgroup productsSubgroup;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime registerDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updateDate;

}
