package com.prod.bud.repository;

import com.prod.bud.model.Product;
import com.prod.bud.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ItemSaleRepository extends JpaRepository<SaleItem,Integer> {


}
