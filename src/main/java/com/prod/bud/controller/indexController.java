package com.prod.bud.controller;

import com.prod.bud.service.SaleService;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class indexController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("vendas", saleService.findAll());
        model.addAttribute("fullValue", saleService.returnsFullValueSales());
        return "/index/index";
    }

    @DeleteMapping
    public ModelAndView save (@RequestParam Integer id)
    {
        try
        {
            saleService.deleteSaleEndRestoreStock(id);
            return new ModelAndView("redirect:/", HttpStatus.SEE_OTHER);
        }catch (PersistenceException pException)
        {
            System.out.println("Erro ao deletar o usuarios" + pException.getMessage());
            return new ModelAndView("redirect:/", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
