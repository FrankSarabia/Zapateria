package com.example.ProyectoCalzado.service;

import com.example.ProyectoCalzado.model.Zapato;
import com.example.ProyectoCalzado.repository.ZapatoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZapatoService {

    @Autowired
    private ZapatoDAO zapatoDAO;

    public List<Zapato> findAll(){
        return zapatoDAO.findAll();
    }
    public Optional<Zapato> findBySku(String sku){
        return zapatoDAO.findById(sku);
    }
    public List<Zapato> findByMarca(String marca){
        return zapatoDAO.findByMarca(marca);
    }
    public List<Zapato> findByModelo(String modelo){
        return zapatoDAO.findByModelo(modelo);
    }
    public Zapato saveZapato(Zapato zapato){
        return zapatoDAO.save(zapato);
    }
    public Optional<Zapato> updateZapato(String sku, Zapato zapato){
        Optional<Zapato> oldZapato = zapatoDAO.findById(sku);
        if(oldZapato.isPresent()){
            Zapato nuevoZapato = oldZapato.get();
            nuevoZapato.setMarca(zapato.getMarca());
            nuevoZapato.setTalla(zapato.getTalla());
            nuevoZapato.setPrecio(zapato.getPrecio());
            nuevoZapato.setModelo(zapato.getModelo());
            return Optional.of(zapatoDAO.save(nuevoZapato));
        }
        return Optional.empty();
    }
    public void deleteZapato(String sku){
        zapatoDAO.deleteById(sku);
    }

}
