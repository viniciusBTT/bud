package com.prod.bud.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double value;

    private Date date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SaleItem> itens = new ArrayList<>();

    public Sale(){}



    // Método para adicionar uma lista de itens à lista existente
    public void addItems(List<SaleItem> items) {
        this.itens.addAll(items);
    }
}