package com.codigo.cursos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/saludo")
public class SaludoController {

    @GetMapping("/hola")
    public String saludar(){
        return "Hola desde controller";
    }



}
