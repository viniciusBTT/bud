package com.prod.bud.controller;

import com.prod.bud.dto.sale.SaleDTO;
import com.prod.bud.model.Sale;
import com.prod.bud.model.SaleItem;
import com.prod.bud.service.SaleItemService;
import com.prod.bud.service.ProductsService;
import com.prod.bud.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController
{
    @Autowired
    private ProductsService productsService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleItemService itemSaleService;


    @GetMapping
    public String sale(Model model)
    {
        model.addAttribute("products",productsService.findAllByQuantity());
        return "/sale/sale";
    }
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Sale> createSale(@RequestBody SaleDTO saleDTO) {
        try {
            Sale sale = saleService.createSale(saleDTO);
            return new ResponseEntity<Sale>(sale , HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @PostMapping
//    @ResponseBody
//    public  Sale saveSale(@RequestBody List<SaleDTO> saleItems)
//    {
//        List<SaleItem> fullSaleItems = productsService.getCompleteSaleItems(saleItems);
//
//        return newSale;
//    }
}
