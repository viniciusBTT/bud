package com.prod.bud.service;

import com.prod.bud.model.Product;
import com.prod.bud.model.Sale;
import com.prod.bud.model.SaleItem;
import com.prod.bud.repository.ItemSaleRepository;
import com.prod.bud.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    public Sale findById(Integer id)
    {
        return saleRepository.findById(id).orElse(null);
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }


    public Double getFullValue(List<SaleItem> saleItems)
    {
        Double fullValue = 0.0;
        for (SaleItem saleItem : saleItems)
        {
            fullValue += saleItem.getProduct().getValor() * saleItem.getQuantitySold();
        }
        return  fullValue;
    }

    @Transactional
    public Sale saveSale(Sale sale) {
        // Salvando a venda
        sale = saleRepository.save(sale);

        // Associando a venda aos itens e salvando os itens
        for (SaleItem item : sale.getItems())
        {
            item.setSale(sale);
            itemSaleRepository.save(item);
        }
        return sale;
    }


    public Sale save ( Sale sale)
    {
        return saleRepository.save(sale);
    }

}
