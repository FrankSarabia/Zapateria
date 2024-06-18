package com.example.ProyectoCalzado.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    @GetMapping("/nuevaVenta")
    public ResponseEntity<String> nuevaVenta(){
        return null;
    }
}
