package com.prod.bud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


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
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @NotNull
    private Integer quantitySold;


    public SaleItem(Product product,Integer quantitySold)
    {
        this.product = product;
        this.quantitySold = quantitySold;
    }

    public SaleItem(){}
}
