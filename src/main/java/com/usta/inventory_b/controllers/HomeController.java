package com.usta.inventory_b.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
        @GetMapping("/")
        public String indehomex(Model model) {
            model.addAttribute("activeMenu","home");
            return "index";
        }
}
