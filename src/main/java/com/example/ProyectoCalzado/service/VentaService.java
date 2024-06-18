package com.example.ProyectoCalzado.service;


import com.example.ProyectoCalzado.model.VentaZapato;
import com.example.ProyectoCalzado.model.Zapato;
import com.example.ProyectoCalzado.repository.VentaDAO;
import com.example.ProyectoCalzado.repository.ZapatoDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    private VentaDAO ventaDAO;
    @Autowired
    private ZapatoDAO zapatoDAO;

    public VentaZapato saveVenta(VentaZapato ventaZapato){
        String sku= ventaZapato.getZapato().getSku();
        Zapato zapato = zapatoDAO.findById(sku).orElse(null);
        if(zapato==null){
            throw new EntityNotFoundException("No se encontro zapato con SKU: "+ sku);
        } else if ((zapato.getCantidad()-ventaZapato.getCantidad())<0) {
            throw new IllegalArgumentException("No se puede realizar esta venta, no hay suficiente inventario");
        }
        zapato.setCantidad(zapato.getCantidad()-ventaZapato.getCantidad());
        zapatoDAO.save(zapato);
        ventaZapato.setZapato(zapato);
        return ventaDAO.save(ventaZapato);
    }

    public List<VentaZapato> listAll(){
        return ventaDAO.findAll();
    }

    public VentaZapato listById(Long id){
        return ventaDAO.findById(id).orElse(null);
    }

    public VentaZapato updateVenta(VentaZapato nuevaVenta) {
        Optional<VentaZapato> oldVenta = ventaDAO.findById(nuevaVenta.getIdVenta());
        if (oldVenta.isPresent()) {
            //Conseguimos el objeto viejo
            VentaZapato ventaZapato=oldVenta.get();
            //Conseguimos el zapato correspondiente
            String oldSku=ventaZapato.getZapato().getSku();
            Zapato oldZapato=zapatoDAO.findById(oldSku).orElseThrow();
            //Aquí le agregamos al objeto viejo de zapato la cantidad que se le habia quitado en esta venta
            oldZapato.setCantidad(ventaZapato.getCantidad()+oldZapato.getCantidad());
            zapatoDAO.save(oldZapato);
            //Ahora le quitamos al nuevo zapato la cantidad correspondiente
            String nuevoSku=nuevaVenta.getZapato().getSku();
            Zapato nuevoZapato=zapatoDAO.findById(nuevoSku).orElseThrow();
            if (nuevoZapato.getCantidad()-nuevaVenta.getCantidad()<0){
                throw new IllegalArgumentException("No se puede realizar la modificación debido a un problema con el inventario");
            }
            nuevoZapato.setCantidad(nuevoZapato.getCantidad()-nuevaVenta.getCantidad());
            zapatoDAO.save(nuevoZapato);
            ventaZapato.setZapato(nuevoZapato);
            ventaZapato.setCantidad(nuevaVenta.getCantidad());
            return ventaDAO.save(ventaZapato);
        }
        return null;
    }

    public void deleteVenta(Long id){
        VentaZapato ventaZapato = ventaDAO.findById(id).orElseThrow();
        String sku = ventaZapato.getZapato().getSku();
        Zapato oldZapato = zapatoDAO.findById(sku).orElseThrow();
        if(oldZapato.getCantidad()-ventaZapato.getCantidad()<0){
            throw new IllegalArgumentException("No se puede eliminar la entrega, problema con el inventario");
        }
        oldZapato.setCantidad(oldZapato.getCantidad()-ventaZapato.getCantidad());
        zapatoDAO.save(oldZapato);
        ventaDAO.deleteById(id);
    }
}
