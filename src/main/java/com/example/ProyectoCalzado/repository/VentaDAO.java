package com.example.ProyectoCalzado.repository;

import com.example.ProyectoCalzado.model.VentaZapato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDAO extends JpaRepository<VentaZapato,Long> {
}
