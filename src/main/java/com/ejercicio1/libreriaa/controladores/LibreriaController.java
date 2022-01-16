package com.ejercicio1.libreriaa.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class LibreriaController {
    
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
