package com.example.ProyectoCalzado.controller;


import com.example.ProyectoCalzado.model.VentaZapato;
import com.example.ProyectoCalzado.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping("/listar")
    public List<VentaZapato> listAll(){
        return ventaService.listAll();
    }

    @GetMapping("/listar/id")
    public VentaZapato listById(@RequestParam Long id){
        return ventaService.listById(id);
    }

    @PostMapping("/agregar")
    public VentaZapato nuevaVenta(@RequestBody VentaZapato ventaZapato){
        return ventaService.saveVenta(ventaZapato);
    }

    @PutMapping("/actualizar")
    public VentaZapato updateVenta(@RequestBody VentaZapato ventaZapato){
        return ventaService.updateVenta(ventaZapato);
    }

    @DeleteMapping("/eliminar")
    public String deleteVenta(@RequestParam Long id){
        ventaService.deleteVenta(id);
        return "Venta eliminada correctamente!";
    }
}
