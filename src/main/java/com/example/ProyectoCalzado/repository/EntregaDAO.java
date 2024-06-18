package com.example.ProyectoCalzado.repository;

import com.example.ProyectoCalzado.model.EntregaZapato;
import com.example.ProyectoCalzado.model.Zapato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregaDAO extends JpaRepository<EntregaZapato, Long> {
}
