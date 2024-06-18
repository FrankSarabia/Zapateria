package com.example.ProyectoCalzado.service;

import com.example.ProyectoCalzado.model.EntregaZapato;
import com.example.ProyectoCalzado.model.Zapato;
import com.example.ProyectoCalzado.repository.EntregaDAO;
import com.example.ProyectoCalzado.repository.ZapatoDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {
    @Autowired
    private EntregaDAO entregaDAO;
    @Autowired
    private ZapatoDAO zapatoDAO;

    public EntregaZapato saveEntrega(EntregaZapato entregaZapato) {
        String sku = entregaZapato.getZapato().getSku();
        Zapato zapato = zapatoDAO.findById(sku).orElse(null);
        if (zapato == null) {
            throw new EntityNotFoundException("No se encontro zapato con SKU: " + sku);
        }
        zapato.setCantidad(zapato.getCantidad() + entregaZapato.getCantidad());
        zapatoDAO.save(zapato);
        entregaZapato.setZapato(zapato);
        return entregaDAO.save(entregaZapato);
    }

    public List<EntregaZapato> listAll() {
        return entregaDAO.findAll();
    }

    public EntregaZapato listById(Long id) {
        return entregaDAO.findById(id).orElse(null);
    }

    public EntregaZapato updateEntrega(EntregaZapato nuevaEntrega) {
        Optional<EntregaZapato> oldEntrega = entregaDAO.findById(nuevaEntrega.getIdRecibo());
        if (oldEntrega.isPresent()) {
            //Conseguimos el objeto viejo
            EntregaZapato entregaZapato = oldEntrega.get();
            //Conseguimos el zapato correspondiente
            String oldSku=entregaZapato.getZapato().getSku();
            Zapato oldZapato=zapatoDAO.findById(oldSku).orElseThrow();
            //Aquí le quitamos al objeto viejo de zapato la cantidad que se le habia asignado en esta entrega
            if((oldZapato.getCantidad()-entregaZapato.getCantidad())<0){
                throw new IllegalArgumentException("No se puede realizar la modificación debido a un problema con el inventario");
            }
            oldZapato.setCantidad(oldZapato.getCantidad()-entregaZapato.getCantidad());
            zapatoDAO.save(oldZapato);
            //Ahora la cantidad se la asignamos al nuevo zapato
            String nuevoSku=nuevaEntrega.getZapato().getSku();
            Zapato nuevoZapato=zapatoDAO.findById(nuevoSku).orElseThrow();
            nuevoZapato.setCantidad(nuevoZapato.getCantidad() + nuevaEntrega.getCantidad());
            entregaZapato.setZapato(nuevoZapato);
            entregaZapato.setCantidad(nuevaEntrega.getCantidad());
            return entregaDAO.save(entregaZapato);
        }
        return null;
    }

    public void deleteEntrega(Long id){
        EntregaZapato entregaZapato = entregaDAO.findById(id).orElseThrow();
        String sku = entregaZapato.getZapato().getSku();
        Zapato zapato = zapatoDAO.findById(sku).orElseThrow();
        zapato.setCantidad(zapato.getCantidad()+entregaZapato.getCantidad());
        zapatoDAO.save(zapato);
        entregaDAO.deleteById(id);
    }
}
