package com.prod.bud.service;

import com.prod.bud.model.Product;
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

    public List<Product> findAll()
    {
        return  repository.findAll();
    }

    public Product save(Product product)
    {
        return repository.save(product);
    }

    public List<Product> getByQuantity() {
        return repository.findByQuantityGreaterThan(1);
    }
}
