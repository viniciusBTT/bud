package com.prod.bud.repository;

import com.prod.bud.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer> {
    @Query("SELECT SUM(s.value) FROM Sale s")
    Double getTotalSalesValue();
}
