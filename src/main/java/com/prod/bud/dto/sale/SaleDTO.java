package com.prod.bud.dto.sale;

import java.util.List;

public record SaleDTO(List<SaleItemDTO > saleItens, Double value) {
}
