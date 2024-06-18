package com.example.ProyectoCalzado.service;

import com.example.ProyectoCalzado.model.EntregaZapato;
import com.example.ProyectoCalzado.model.Zapato;
import com.example.ProyectoCalzado.repository.EntregaDAO;
import com.example.ProyectoCalzado.repository.ZapatoDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {
    @Autowired
    private EntregaDAO entregaDAO;
    @Autowired
    private ZapatoDAO zapatoDAO;

    public EntregaZapato saveEntrega(EntregaZapato entregaZapato){
        String sku = entregaZapato.getZapato().getSku();
        Zapato zapato = zapatoDAO.findById(sku).orElse(null);
        if(zapato==null){
            throw new EntityNotFoundException("No se encontro zapato con SKU: "+ sku);
        }
        zapato.setCantidad(zapato.getCantidad()+entregaZapato.getCantidad());
        zapatoDAO.save(zapato);
        entregaZapato.setZapato(zapato);
        return entregaDAO.save(entregaZapato);
    }

    public List<EntregaZapato> listAll(){
        return entregaDAO.findAll();
    }

    public EntregaZapato listById(Long id){
        return entregaDAO.findById(id).orElse(null);
    }
}
