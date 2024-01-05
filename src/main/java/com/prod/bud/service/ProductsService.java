package com.prod.bud.service;

import com.prod.bud.dto.sale.SaleSaveDTO;
import com.prod.bud.model.Product;
import com.prod.bud.model.Sale;
import com.prod.bud.model.SaleItem;
import com.prod.bud.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository repository;

    public Product findById(Integer id)
    {
        return repository.findById(id).orElse(null);
    }

    public List<Product> findByIds(List<Integer> ids) {
        return repository.findAllById(ids);
    }

    public List<Product> findAll()
    {
        return  repository.findAll();
    }

    public Product save(Product product)
    {
        return repository.save(product);
    }

    public List<Product> findAllByQuantity() {
        return repository.findByQuantityGreaterThan(1);
    }



    public List<SaleItem> getCompleteSaleItems(List<SaleSaveDTO> saleItems) {
        List<SaleItem> saleFullItens = new ArrayList<>();

        for (SaleSaveDTO saleItemDTO : saleItems) {
            // Suponho que você tenha um método no repositório para obter o Product pelo ID
            Product product = this.findById(saleItemDTO.id());

            if (product != null) {
                SaleItem saleItem = new SaleItem();
                saleItem.setProduct(product);
                saleItem.setQuantitySold(saleItemDTO.quantity());

                // Adicione o SaleItem completo à lista
                saleFullItens.add(saleItem);
            }
        }

        return saleFullItens;
    }

}
