package com.prod.bud.controller;

import com.prod.bud.dto.sale.SaleDTO;
import com.prod.bud.dto.sale.SaleItemDTO;
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
import org.springframework.web.servlet.ModelAndView;
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
    @PostMapping("/create")
    public ModelAndView  createSale(@RequestBody SaleDTO saleItemDto, RedirectAttributes ra)
    {
        try
        {
          saleService.createSaleEndSaveSale(saleItemDto.saleItens());
          ra.addAttribute("success","Venda registrada com sucesso");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            ra.addAttribute("error","Falha ao registrar a venda");
        }
        return new ModelAndView("redirect:/sale", HttpStatus.SEE_OTHER);
    }
}
