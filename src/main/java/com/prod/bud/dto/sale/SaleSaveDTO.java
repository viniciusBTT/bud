package com.prod.bud.dto.sale;

import com.prod.bud.model.SaleItem;

import java.util.List;

public record SaleSaveDTO( List<SaleItem> saleItens, Double valor){
}
