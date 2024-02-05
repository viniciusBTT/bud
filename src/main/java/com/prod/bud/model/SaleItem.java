package com.prod.bud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Cascade;


@Data
@Entity
public class SaleItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Product product;


    @NotNull
    private Integer quantitySold;


    public SaleItem(Product product,Integer quantitySold)
    {
        this.product = product;
        this.quantitySold = quantitySold;
    }

    public SaleItem(){}
}
