package com.example.ProyectoCalzado.repository;

import com.example.ProyectoCalzado.model.Zapato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZapatoDAO extends JpaRepository<Zapato,String> {
    List<Zapato> findByMarca(String marca);
    List<Zapato> findByModelo(String modelo);
}
