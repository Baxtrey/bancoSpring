package com.banco.bancobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.bancobackend.model.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Integer>{
	
	public Optional<Gestor> findOneByCorreo(String correo);
	
	public Optional<Gestor> findOneByCorreoAndPassword(String correo, String password);

}
