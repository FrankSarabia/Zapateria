package com.example.ProyectoCalzado.controller;


import com.example.ProyectoCalzado.model.EntregaZapato;
import com.example.ProyectoCalzado.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrega")
public class EntregaController {
    @Autowired
    private EntregaService entregaService;

    @GetMapping("/listar")
    public List<EntregaZapato> listAll(){
        return entregaService.listAll();
    }

    @GetMapping("/listar/id")
    public EntregaZapato listById(@RequestParam Long id){
        return entregaService.listById(id);
    }

    @PostMapping("/agregar")
    public EntregaZapato addNew(@RequestBody EntregaZapato nuevaEntrega){
        return entregaService.saveEntrega(nuevaEntrega);
    }

    @PutMapping("/actualizar")
    public EntregaZapato updateEntrega(@RequestBody EntregaZapato nuevaEntrega){
        return entregaService.updateEntrega(nuevaEntrega);
    }

    @DeleteMapping("/eliminar")
    public String deleteEntrega(@RequestParam Long id){
        entregaService.deleteEntrega(id);
        return "Entrega eliminada correctamente!";
    }
}
