package com.prod.bud.service;

import com.prod.bud.model.Product;
import com.prod.bud.model.Sale;
import com.prod.bud.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public Sale findById(Integer id)
    {
        return repository.findById(id).orElse(null);
    }

    public List<Sale> findAll() {
        return repository.findAll();
    }

    public Sale save ( Sale sale)
    {
        return repository.save(sale);
    }

}
