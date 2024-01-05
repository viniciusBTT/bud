package com.prod.bud.controller;

import com.prod.bud.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
public class StockController
{
    @Autowired
    private ProductsService productsService;
    @GetMapping
    public String stock(Model model)
    {
        model.addAttribute("products", productsService.findAll() );
        return "stock/stock";
    }
}
