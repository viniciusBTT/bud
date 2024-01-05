package com.prod.bud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date saleDate ;

    @NotNull
    private Double valor;

    @OneToMany(fetch = FetchType.EAGER)
    private List<SaleItem> items = new ArrayList<>();

    public Sale(List<SaleItem> saleItens, Double valor)
    {
        this.items = saleItens;
        this.valor = valor;
    }
    public Sale( Double valor)
    {
        this.saleDate = new Date();
        this.valor = valor;
    }





}