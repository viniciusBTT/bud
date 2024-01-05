package com.prod.bud.service;

import com.prod.bud.model.SaleItem;
import com.prod.bud.repository.ItemSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleItemService {
    @Autowired
    private ItemSaleRepository repository;

    public List<SaleItem> saveItemSale (List<SaleItem> saleItems)
    {
        return repository.saveAll(saleItems);
    }
}
