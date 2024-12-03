package com.example.springdatapoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador que fornece método para lidar com as requisições HTTP relacionadas ao index.
 */
@Controller
public class IndexController {

    /**
     * Exibe a página inicial index
     *
     * @param model o modelo para a view
     * @return o nome da view para exibir o index
     */
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}
