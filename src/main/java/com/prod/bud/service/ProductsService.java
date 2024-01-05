package com.prod.bud.service;

import com.prod.bud.model.Product;
import com.prod.bud.model.Sale;
import com.prod.bud.model.SaleItem;
import com.prod.bud.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    public List<SaleItem> getCompleteSaleItems(List<SaleItem> saleItems, Sale sale) {

        // Busca os objetos completos a partir dos IDs
        for (SaleItem saleItem : saleItems) {
            Product product = repository.findById(saleItem.getProduct().getId()).orElse(null);
            saleItem.setProduct(product);
            saleItem.setSale(sale);
        }

        return saleItems;
    }

}
