package com.prod.bud.service;

import com.prod.bud.dto.sale.SaleDTO;
import com.prod.bud.dto.sale.SaleItemDTO;
import com.prod.bud.model.Product;
import com.prod.bud.model.Sale;
import com.prod.bud.model.SaleItem;
import com.prod.bud.repository.ItemSaleRepository;
import com.prod.bud.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    @Autowired
    private ProductsService productsService;

    public Sale createSaleEndSaveSale(List<SaleItemDTO> saleIntens)
    {
        Sale sale = new Sale();
        List<SaleItem> itensSale = new ArrayList<>();

        for (SaleItemDTO iten : saleIntens)
        {
            Product product = productsService.findById(iten.productID());
            product.setQuantity( product.getQuantity() - iten.quantity());
            itensSale.add( new SaleItem(product, iten.quantity()));
            sale.setValue((product.getValor() * iten.quantity()) + sale.getValue());
        }
        sale.addItems(itensSale);
        sale.setDate(new Date());
        sale = saleRepository.save(sale);

        return sale;
    }

    public Sale findById(Integer id)
    {
        return saleRepository.findById(id).orElse(null);
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }



    public Sale save (Sale sale)
    {
        return saleRepository.save(sale);
    }

    public  void deleteSaleEndRestoreStock (Integer id)
    {
        Sale sale = this.findById(id);
        List<SaleItem> listaDeIntens = sale.getItens();
        for (SaleItem iten : listaDeIntens)
        {
            Product product = productsService.findById(iten.getProduct().getId());
            product.setQuantity(product.getQuantity() + iten.getQuantitySold());
            productsService.save(product);
        }
        saleRepository.deleteById(id);

    }

    public double returnsFullValueSales()
    {
        var fullValue = saleRepository.getTotalSalesValue();
        if (fullValue != null)
            return fullValue;

       return 0;
    }

}
