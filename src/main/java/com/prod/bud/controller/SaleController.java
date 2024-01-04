package com.prod.bud.controller;

import com.prod.bud.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public String sale(Model model)
    {
        model.addAttribute("products",productsService.getByQuantity());
        return "/sale/sale";
    }
}
