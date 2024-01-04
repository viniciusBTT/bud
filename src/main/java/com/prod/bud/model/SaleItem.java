package com.prod.bud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
@Entity
public class SaleItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Sale sale;

    @NotNull
    private Integer quantitySold;

    @NotNull
    private double totalAmount;
}
