package com.banco.bancobackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.bancobackend.model.Gestor;
import com.banco.bancobackend.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    public Optional<Mensaje> findByOrigen(Gestor origen);

    public Optional<Mensaje> findByDestino(Gestor destino);

    public List<Mensaje> findAllByOrigenAndDestino(Gestor origen, Gestor destino);

}
