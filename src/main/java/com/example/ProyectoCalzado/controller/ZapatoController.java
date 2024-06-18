package com.example.ProyectoCalzado.controller;


import com.example.ProyectoCalzado.model.Zapato;
import com.example.ProyectoCalzado.service.ZapatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zapato")
public class ZapatoController {
    @Autowired
    private ZapatoService zapatoService;

    @GetMapping("/listar")
    public List<Zapato> getZapatos() {
        return zapatoService.findAll();
    }

    @GetMapping("/listar/marca")
    public List<Zapato> getZapatosByMarca(@RequestParam String marca) {
        return zapatoService.findByMarca(marca);
    }

    @GetMapping("/listar/modelo")
    public List<Zapato> getZapatosByModelo(@RequestParam String modelo){
        return zapatoService.findByModelo(modelo);
    }

    @GetMapping("/listar/sku")
    public Optional<Zapato> getZapatoBySku(@RequestParam String sku){
        return zapatoService.findBySku(sku);
    }

    @PostMapping("/agregar")
    public Zapato addZapato(@RequestBody Zapato zapato){
        return zapatoService.saveZapato(zapato);
    }

    @PutMapping("/actualizar")
    public Optional<Zapato> updateZapato(@RequestParam String sku, @RequestBody Zapato zapato){
        return zapatoService.updateZapato(sku,zapato);
    }

    @DeleteMapping("/eliminar")
    public String deleteZapato(@RequestParam String sku){
        zapatoService.deleteZapato(sku);
        return "Eliminado correctamente!";
    }

}
