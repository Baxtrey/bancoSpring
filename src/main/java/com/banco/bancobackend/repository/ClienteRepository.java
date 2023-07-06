package com.banco.bancobackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.bancobackend.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public Optional<Cliente> findByCorreo(String correo);

    public Optional<Cliente> findByUsuario(String usuario);
    
    public List <Cliente> findByGestorId (Integer idGestor);
}
