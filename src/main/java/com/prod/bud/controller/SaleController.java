package com.prod.bud.controller;

import com.prod.bud.dto.sale.SaleSaveDTO;
import com.prod.bud.model.Sale;
import com.prod.bud.model.SaleItem;
import com.prod.bud.service.SaleItemService;
import com.prod.bud.service.ProductsService;
import com.prod.bud.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping
    @ResponseBody
    public  Sale saveSale(@RequestBody List<SaleSaveDTO> saleItems)
    {
        List<SaleItem> fullSaleItems = productsService.getCompleteSaleItems(saleItems);
        Double fullValue = saleService.getFullValue(fullSaleItems);
        Sale newSale = new Sale(fullSaleItems , fullValue);
        return newSale;
    }
}
