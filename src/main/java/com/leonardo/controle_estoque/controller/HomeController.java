package com.leonardo.controle_estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/estoque")
    public String acessarHome() {
        return "estoque/home";
    }
}
