package com.usta.inventory_b.controllers;

import com.usta.inventory_b.entities.ProductEntity;
import com.usta.inventory_b.models.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewProducts(Model model,
                               @RequestParam(required = false) Long idProd,
                               @RequestParam(required = false) String nombreProd,
                               @RequestParam(required = false) String categoria,
                               @RequestParam(required = false) String marca) {
        List<ProductEntity> products = productService.filterProducts(nombreProd,categoria,marca,idProd);
        model.addAttribute("activeMenu", "products");
        model.addAttribute("products", products);
        model.addAttribute("idProd", idProd);
        model.addAttribute("nombreProd", nombreProd);
        model.addAttribute("categoria", categoria);
        model.addAttribute("marca", marca);
        return "products/listProducts";
    }

    /* -------------------------------------------- */

    @GetMapping("/new")
    public String ShowCreateForm(Model model) {
        model.addAttribute("title", "Create Product");
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("activeMenu", "products");
        return "products/newProduct";
    }
}
